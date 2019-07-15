package net.tkuboi.gradzilla.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignmentController {
  @Autowired
  AssignmentRepository assignmentRepository;
  @GetMapping("/assignments/{course}")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<Assignment> getAssignments(@PathVariable("course") String course) {
    return assignmentRepository.findAllByCourseEqualsOrderByName(course);
  }
}
