package net.tkuboi.gradzilla.course;

import net.tkuboi.gradzilla.utils.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping(value="/courses/put")
  public Course saveCourse(@RequestBody Course course) {
    this.courseRepository.save(course);
    return course;
  }

  @PostMapping(value="/courses/delete/{name}")
  public RestResponseDto deleteCourse(@PathVariable(value="name") String name,
                                      @RequestBody Course course) {
    RestResponseDto result = new RestResponseDto();
    result.setStatus(RestResponseDto.Status.ERROR.name());
    this.courseRepository.delete(course);
    result.setStatus(RestResponseDto.Status.SUCCESS.name());
    return result;
  }
}
