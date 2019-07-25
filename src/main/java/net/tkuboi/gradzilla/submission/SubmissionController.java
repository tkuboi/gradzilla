package net.tkuboi.gradzilla.submission;

import net.tkuboi.gradzilla.grader.GraderResultDto;
import net.tkuboi.gradzilla.grader.GraderRepository;
import net.tkuboi.gradzilla.grader.GraderService;
import net.tkuboi.gradzilla.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SubmissionController {

  private FileStorageProperties fileStorageProperties;
  private SubmissionRepository submissionRepository;
  private GraderRepository graderRepository;

  @Autowired
  public SubmissionController(FileStorageProperties fileStorageProperties,
                              SubmissionRepository submissionRepository,
                              GraderRepository graderRepository) {
    this.fileStorageProperties = fileStorageProperties;
    this.submissionRepository = submissionRepository;
    this.graderRepository = graderRepository;
  }

  @PostMapping(value="/submissions/put/{userId}")
  protected SubmissionResponse doPost(@PathVariable(value="userId") String userId,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("filename") String filename,
                          @RequestParam("assignmentId") Integer assignmentId,
                          @RequestParam("course") String course,
                          @RequestParam("assignmentName") String assignment) {
    Path targetPath = null;
    String message = "";
    try {
      String dir = fileStorageProperties.getUploadDir()
        + "/" + userId
        + "/" + course.replaceAll("\\s+", "_")
        + "/" + assignment.replaceAll("\\s+", "_");
      targetPath = Paths.get(dir);
      if (!Files.exists(targetPath)) Files.createDirectories(targetPath);
      targetPath = Paths.get(dir + "/" + filename);
      Files.copy(file.getInputStream(), targetPath, REPLACE_EXISTING);
      Submission submission =
        new Submission(assignmentId, userId, new Timestamp(System.currentTimeMillis()), targetPath.toString());
      submissionRepository.save(submission);
      GraderService graderService = new GraderService(
        graderRepository.findAllByAssignmentOrderBySeq(assignmentId));
      GraderResultDto gradeResult = graderService.grade(targetPath);
      submission.setResult(gradeResult.getResult());
      message = gradeResult.getResult();
      submission.setScore(gradeResult.getScore());
      submission.setStatus(Submission.Status.GRADED.toString());
      submissionRepository.save(submission);
    } catch (IOException e) {
      e.printStackTrace();
      return new SubmissionResponse("error", e.getMessage(), "");
    }
    return new SubmissionResponse("success", message, targetPath.toString());
  }

  @GetMapping(value = "/submissions/{userId}/{assignmentId}")
  public List<Submission> getSubmissions(@PathVariable("userId") String userId, @PathVariable("assignmentId") Integer assignmentId) {
    return submissionRepository.findAllByAssignmentAndUserOrderBySubmissionTimeDesc(assignmentId, userId);
  }

  @GetMapping(value = "/submission/{submissionId}")
  public Submission getSubmission(@PathVariable(value = "submissionId") Integer submissionId) {
    return submissionRepository.findById(submissionId).get();
  }
}
