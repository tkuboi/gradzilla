package net.tkuboi.gradzilla.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping(value = "/courses")
  public List<Course> getCourses() {
    return this.courseRepository.findAll();
  }

  @GetMapping(value = "/{userId}/courses")
  public List<Course> getCoursesByUser(@PathVariable(value="userId") String userId) {
    return this.courseRepository.findAllByUser(userId);
  }
}
