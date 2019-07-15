package net.tkuboi.gradzilla.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping(value = "/courses")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<Course> getCourses() {
    return this.courseRepository.findAll();
  }
}
