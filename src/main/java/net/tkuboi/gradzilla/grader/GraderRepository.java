package net.tkuboi.gradzilla.grader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface GraderRepository extends JpaRepository<Grader, Integer> {
  List<Grader> findAllByAssignmentOrderBySeq(int assignmentId);
  List<Grader> findAllByAssignmentOrderBySeqDesc(int assignmentId);
  @Query(value="SELECT g FROM Grader as g JOIN Assignment as a ON a.id = g.assignment AND a.course = :course AND g.type = :type")
  List<Grader> findAllByCourseAndType(String course, String type);
}
