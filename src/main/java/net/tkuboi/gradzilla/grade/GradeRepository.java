package net.tkuboi.gradzilla.grade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, String> {
  List<Grade> findAllById_User(String user);
  Grade findById(GradeId id);
}
