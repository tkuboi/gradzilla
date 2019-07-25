package net.tkuboi.gradzilla.user;

public class UserDto {

  private String username;
  private String email;
  private String first;
  private String last;
  private String password;
  private String role;
  private Boolean active;
  private String course;
  private Integer section;
  private String quarter;
  private Integer year;

  public UserDto() {
    this.username = null;
    this.email = null;
    this.first = null;
    this.last = null;
    this.password = null;
  }

  public UserDto(String username, String email, String first, String last,
                 String password, String role, boolean active, String course,
                 Integer section, String quarter, Integer year) {
    this.username = username;
    this.email = email;
    this.first = first;
    this.last = last;
    this.password = password;
    this.role = role;
    this.active = active;
    this.course = course;
    this.section = section;
    this.quarter = quarter;
    this.year = year;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public enum Role {
    ADMIN,
    STUDENT
  }
}
