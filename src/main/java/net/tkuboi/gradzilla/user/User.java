package net.tkuboi.gradzilla.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

  public static enum Role {
    ADMIN,
    STUDENT;
  };

  @Id
  @Column(name="name")
  private String name;
  @Column(name="email")
  private String email;
  @Column(name="first")
  private String first;
  @Column(name="last")
  private String last;
  @Column(name="password")
  private String password;
  @Column(name="role")
  private String role;
  @Column(name="active")
  private boolean active;

  public User() {
    this.name = null;
    this.email = null;
    this.first = null;
    this.last = null;
    this.password = null;
  }

  public User(String name, String email, String first, String last, String password) {
    this.name = name;
    this.email = email;
    this.first = first;
    this.last = last;
    this.password = password;
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
}
