package net.tkuboi.gradzilla.user;

public class UserDto {

  private String name;
  private String email;
  private String first;
  private String last;
  private String password;
  private String role;
  private boolean active;

  public UserDto() {
    this.name = null;
    this.email = null;
    this.first = null;
    this.last = null;
    this.password = null;
  }

  public UserDto(String name, String email, String first, String last, String password, String role, boolean active) {
    this.name = name;
    this.email = email;
    this.first = first;
    this.last = last;
    this.password = password;
    this.role = role;
    this.active = active;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public enum Role {
    ADMIN,
    STUDENT
  }
}
