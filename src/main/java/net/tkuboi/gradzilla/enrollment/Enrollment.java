package net.tkuboi.gradzilla.enrollment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
  @Id
  @Column(name="user")
  private String user;
  @Column(name="course")
  private String course;
  @Column(name="section")
  private Integer section;
  @Column(name="year")
  private Integer year;
  @Column(name="quarter")
  private String quarter;

  public Enrollment() {
    this.user = null;
    this.course = null;
    this.section = null;
    this.year = null;
    this.quarter = null;
  }

  public Enrollment(String user, String course, Integer section, Integer year, String quarter) {
    this.user = null;
    this.course = null;
    this.section = null;
    this.year = null;
    this.quarter = null;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public Integer getSection() {
    return section;
  }

  public void setSection(Integer section) {
    this.section = section;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }
}
