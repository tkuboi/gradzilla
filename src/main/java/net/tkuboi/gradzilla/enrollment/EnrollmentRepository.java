package net.tkuboi.gradzilla.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
  Enrollment findById(EnrollmentId id);
}
