package net.tkuboi.gradzilla.enrollment;

import net.tkuboi.gradzilla.grade.GradeId;

import javax.persistence.*;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
  @EmbeddedId
  @AttributeOverrides({ @AttributeOverride(name = "user", column = @Column(name = "user", nullable = false)),
    @AttributeOverride(name = "course", column = @Column(name = "course", nullable = false)),
    @AttributeOverride(name = "year", column = @Column(name = "year", nullable = false)),
    @AttributeOverride(name = "quarter", column = @Column(name = "quarter", nullable = false))})
  private EnrollmentId id;

  @Column(name = "section")
  private Integer section;

  @Column(name = "active")
  private Boolean active;

  public Enrollment() {
    this.id = null;
    this.section = null;
    this.active = null;
  }

  public Enrollment(EnrollmentId id, Integer section, Boolean active) {
    this.id = id;
    this.section = section;
    this.active = active;
  }

  public Enrollment(String user, String course, Integer section, Integer year, String quarter) {
    this.id = new EnrollmentId(user, course, year, quarter);
    this.section = section;
  }

  public Integer getSection() {
    return section;
  }

  public void setSection(Integer section) {
    this.section = section;
  }

  public EnrollmentId getId() {
    return id;
  }

  public void setId(EnrollmentId id) {
    this.id = id;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
