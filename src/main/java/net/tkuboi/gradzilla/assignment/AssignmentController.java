package net.tkuboi.gradzilla.assignment;

import net.tkuboi.gradzilla.quarter.Quarter;
import net.tkuboi.gradzilla.quarter.QuarterRepository;
import net.tkuboi.gradzilla.utils.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {
  @Autowired
  AssignmentRepository assignmentRepository;

  @Autowired
  QuarterRepository quarterRepository;

  @GetMapping("/assignments/{course}")
  public List<Assignment> getAssignments(@PathVariable("course") String course) {
    return assignmentRepository.findAllByCourseAndQuarter(course);
  }

  @GetMapping("/assignments-user/{user}/{course}")
  public List<AssignmentDTO> getUserAssignments(@PathVariable("user") String user,
                                                @PathVariable("course") String course) {
    return assignmentRepository.findAllWithGradeByCourseAndCurrentEnrollmentAndUser(course, user);
  }

  @PostMapping("/assignments/put")
  public Assignment putAssignment(@RequestBody Assignment assignment) {
    Quarter quarter = quarterRepository.findQuarterByCurrentTrue();
    assignment.setYear(quarter.getId().getYear());
    assignment.setQuarter(quarter.getId().getQuarter());
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
