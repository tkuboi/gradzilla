package net.tkuboi.gradzilla.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
  List<Enrollment> findAllByCourse(String course);
  List<Enrollment> findAllByUser(String user);
  List<Enrollment> findAllByUserAndCourse(String user, String course);
}
