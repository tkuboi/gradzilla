package net.tkuboi.gradzilla.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
  List<Assignment> findAllByCourseEqualsOrderByTypeAscIdAsc(String course);
  @Query(value="SELECT a FROM Assignment as a JOIN Quarter as q ON q.id.year = a.year AND q.id.quarter = a.quarter AND q.current = 1 AND a.course = :course ORDER BY a.type asc, a.id asc")
  List<Assignment> findAllByCourseAndQuarter(@Param("course") String course);
  String QUERY_FIND_USER_ASSIGNMENTS = "SELECT a.*, t.name as type_name, g.user, g.grade as grade"
    + " FROM Assignment a "
    + " JOIN AssignmentType t ON t.id=a.type "
    + " JOIN Enrollment e ON e.user = :user and a.course=e.course and a.year=e.year and a.quarter=e.quarter and e.active=1 "
    + " LEFT JOIN Grade g ON g.assignment=a.id AND g.user = e.user "
    + " WHERE a.course = :course";
  @Query(nativeQuery = true)
  List<AssignmentDTO> findAllWithGradeByCourseAndCurrentEnrollmentAndUser(@Param("course") String course, @Param("user") String user);
}
