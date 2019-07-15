package net.tkuboi.gradzilla.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
  List<Course> findAll();
  List<Course> findByName(String name);
}
