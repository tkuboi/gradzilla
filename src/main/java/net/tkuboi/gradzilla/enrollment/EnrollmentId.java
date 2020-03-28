package net.tkuboi.gradzilla.enrollment;

public class EnrollmentId implements java.io.Serializable {
  private String user;
  private String course;
  private Integer year;
  private String quarter;

  public EnrollmentId() {
    this.user = null;
    this.course = null;
    this.year = null;
    this.quarter = null;
  }

  public EnrollmentId(String user, String course, Integer year, String quarter) {
    this.user = user;
    this.course = course;
    this.year = year;
    this.quarter = quarter;
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
