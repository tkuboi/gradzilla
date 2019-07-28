package net.tkuboi.gradzilla.grade;

import javax.persistence.*;

@Entity
@Table(name="Grade")
public class Grade implements java.io.Serializable {
  @EmbeddedId
  @AttributeOverrides({ @AttributeOverride(name = "user", column = @Column(name = "user", nullable = false)),
    @AttributeOverride(name = "assignment", column = @Column(name = "assignment", nullable = false))})
  private GradeId id;
  @Column(name = "course")
  private String course;
  @Column(name = "grade")
  private Integer grade;

  public Grade() {
    this.id = null;
    this.course = null;
    this.grade = null;
  }

  public Grade(GradeId id, String course, Integer grade) {
    this.id = id;
    this.course = course;
    this.grade = grade;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public GradeId getId() {
    return id;
  }

  public void setId(GradeId id) {
    this.id = id;
  }
}
