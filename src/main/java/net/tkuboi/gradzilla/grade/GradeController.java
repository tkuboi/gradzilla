package net.tkuboi.gradzilla.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeController {
  @Autowired
  GradeRepository gradeRepository;

  @GetMapping("/{userId}/grades/{assignmentId}")
  public Grade getGrade(@PathVariable(value="userId") String userId, @PathVariable(value="assignmentId") Integer assignmentId) {
    Grade grade = gradeRepository.findById(new GradeId(userId, assignmentId));
    System.out.println(grade.getGrade());
    return grade;
  }
}
