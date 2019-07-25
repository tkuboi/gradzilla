package net.tkuboi.gradzilla.assignment;

import net.tkuboi.gradzilla.utils.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController {
  @Autowired
  AssignmentRepository assignmentRepository;
  @GetMapping("/assignments/{course}")
  public List<Assignment> getAssignments(@PathVariable("course") String course) {
    return assignmentRepository.findAllByCourseEqualsOrderByTypeAscIdAsc(course);
  }

  @PostMapping("/assignments/put")
  public Assignment putAssignment(@RequestBody Assignment assignment) {
    this.assignmentRepository.save(assignment);
    System.out.println(assignment);
    return assignment;
  }

  @PostMapping("/assignments/delete/{id}")
  public RestResponseDto deleteAssignment(@PathVariable(value="id") int id,
                                          @RequestBody Assignment assignment) {
    RestResponseDto result = new RestResponseDto();
    result.setStatus(RestResponseDto.Status.ERROR.name());
    this.assignmentRepository.delete(assignment);
    result.setStatus(RestResponseDto.Status.SUCCESS.name());
    return result;
  }
}
