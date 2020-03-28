package net.tkuboi.gradzilla.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
  List<Course> findAll();
  Course findByName(String name);
  @Query(value="SELECT c FROM Course as c JOIN Enrollment as e ON c.name=e.id.course AND e.id.user = :user JOIN Quarter as q ON q.id.year=e.id.year AND q.id.quarter=e.id.quarter AND q.current=1")
  List<Course> findAllByUser(@Param("user") String user);
}
