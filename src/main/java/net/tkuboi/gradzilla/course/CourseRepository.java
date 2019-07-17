package net.tkuboi.gradzilla.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
  List<Course> findAll();
  Course findByName(String name);
  @Query(value="SELECT c FROM Course as c JOIN Enrollment as e ON c.name=e.course AND e.user = :user")
  List<Course> findAllByUser(@Param("user") String user);
}
