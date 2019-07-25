package net.tkuboi.gradzilla.assignment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
  List<Assignment> findAllByCourseEqualsOrderByTypeAscIdAsc(String course);
}
