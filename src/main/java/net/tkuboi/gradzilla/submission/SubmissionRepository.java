package net.tkuboi.gradzilla.submission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
  List<Submission> findAllByAssignmentAndUserOrderBySubmissionTimeDesc(int assignment, String user);
}
