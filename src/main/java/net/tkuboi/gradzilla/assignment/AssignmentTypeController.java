package net.tkuboi.gradzilla.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentTypeController {
  private final AssignmentTypeRepository assignmentTypeRepository;

  @Autowired
  public AssignmentTypeController(AssignmentTypeRepository assignmentTypeRepository) {
    this.assignmentTypeRepository = assignmentTypeRepository;
  }

  @GetMapping("/assignment-types")
  public List<AssignmentType> getAll() {
    return this.assignmentTypeRepository.findAll();
  }
}
