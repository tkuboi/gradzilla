package net.tkuboi.gradzilla.grader;

import net.tkuboi.gradzilla.assignment.Assignment;
import net.tkuboi.gradzilla.assignment.AssignmentRepository;
import net.tkuboi.gradzilla.property.FileStorageProperties;
import net.tkuboi.gradzilla.utils.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GraderController {
  private final GraderRepository graderRepository;
  private final AssignmentRepository assignmentRepository;
  private final FileStorageProperties fileStorageProperties;

  @Autowired
  public GraderController(GraderRepository graderRepository,
                          AssignmentRepository assignmentRepository,
                          FileStorageProperties fileStorageProperties) {
    this.graderRepository = graderRepository;
    this.assignmentRepository = assignmentRepository;
    this.fileStorageProperties = fileStorageProperties;
  }

  @GetMapping(value = "/graders/{assignmentId}")
  protected List<Grader> getGraders(@PathVariable("assignmentId") int assignmentId) {
    return this.graderRepository.findAllByAssignmentOrderBySeq(assignmentId);
  }

  @PostMapping(value = "/graders/delete/{graderId}")
  protected RestResponseDto deleteGrader(@PathVariable("graderId") int graderId,
                                         @RequestBody Grader grader) {
    RestResponseDto result = new RestResponseDto();
    result.setStatus(RestResponseDto.Status.ERROR.name());
    if (graderId == grader.getId()) {
      this.graderRepository.delete(grader);
      result.setStatus(RestResponseDto.Status.SUCCESS.name());
    } else {
      result.setMessage("the id does not match.");
    }
    return result;
  }

  @PostMapping(value = "/graders/update/{graderId}")
  protected RestResponseDto updateGrader(@PathVariable("graderId") int graderId,
                                         @RequestBody Grader grader) {
    RestResponseDto result = new RestResponseDto();
    result.setStatus(RestResponseDto.Status.ERROR.name());
    Grader graderOld = this.graderRepository.getOne(graderId);
    if (graderId == graderOld.getId() && grader.getId() == graderOld.getId()) {
      this.graderRepository.save(grader);
      result.setStatus(RestResponseDto.Status.SUCCESS.name());
    } else {
      result.setMessage("the id does not match.");
    }
    return result;
  }

  @PostMapping(value = "/graders/put/{assignmentId}")
  protected RestResponseDto putGrader(@PathVariable("assignmentId") int assignmentId,
                                      @RequestParam("file") MultipartFile file,
                                      @RequestParam("filename") String filename,
                                      @RequestParam("seq") Integer seq,
                                      @RequestParam("course") String course,
                                      @RequestParam("program") String program,
                                      @RequestParam("args")String args,
                                      @RequestParam("copy") Integer copy,
                                      @RequestParam("pylint") Integer pylint,
                                      @RequestParam("type") String type) {
    Path targetPath = null;
    RestResponseDto result = new RestResponseDto();
    try {
      Assignment assignment = this.assignmentRepository.findById(assignmentId).get();
      String dir = fileStorageProperties.getUploadDir()
        + "/grader_programs/" + course.replaceAll("\\s+", "_")
        + "/" + assignment.getName().replaceAll("\\s+", "_");
      targetPath = Paths.get(dir);
      if (!Files.exists(targetPath)) Files.createDirectories(targetPath);
      targetPath = Paths.get(dir + "/" + filename);
      Files.copy(file.getInputStream(), targetPath, REPLACE_EXISTING);
      Grader grader = new Grader(assignmentId,seq,targetPath.toString(),program,args, copy, type);
      graderRepository.save(grader);
      if (pylint != null && pylint.equals(1)) {
        Grader grader1 = addPylint(course, assignmentId);
        this.graderRepository.save(grader1);
      }
      result.setExitCode(0);
      result.setMessage(targetPath.toString());
      result.setStatus(RestResponseDto.Status.SUCCESS.name());
      result.setObject(grader);
    } catch (Exception e) {
      e.printStackTrace();
      result.setExitCode(1);
      result.setMessage(e.getMessage());
      result.setStatus(RestResponseDto.Status.ERROR.name());
    }
    return result;
  }

  private Grader addPylint(String course, int assignmentId) {
    Grader lint = this.graderRepository.findTopByCourseAndType(course, Grader.Type.LINT.name());
    if (lint == null) {
      throw new RuntimeException("The lint has not been registered for this course yet.");
    }
    List<Grader> graders = this.graderRepository.findAllByAssignmentOrderBySeqDesc(assignmentId);
    int seq = 0;
    if (graders != null && graders.size() > 0) {
      seq = graders.get(0).getSeq() + 1;
    }
    return new Grader(
      assignmentId, seq, lint.getFilePath(), lint.getProgram(),
      lint.getArgs(), lint.getCopy(), lint.getType());
  }
}
