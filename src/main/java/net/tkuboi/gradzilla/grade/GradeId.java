package net.tkuboi.gradzilla.grade;

public class GradeId implements java.io.Serializable {
  private String user;
  private Integer assignment;

  public GradeId() {
    this.user = null;
    this.assignment = null;
  }

  public GradeId(String user, Integer assignment) {
    this.user = user;
    this.assignment = assignment;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Integer getAssignment() {
    return assignment;
  }

  public void setAssignment(Integer assignment) {
    this.assignment = assignment;
  }
}
