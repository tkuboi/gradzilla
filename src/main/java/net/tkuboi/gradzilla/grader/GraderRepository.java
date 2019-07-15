package net.tkuboi.gradzilla.grader;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraderRepository extends JpaRepository<Grader, Integer> {
  List<Grader> findAllByAssignmentOrderBySeq(int assignment);
}
