package net.tkuboi.gradzilla.assignment;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Assignment")
public class Assignment {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "course")
  private String course;
  @Column(name = "name")
  private String name;
  @Column(name = "type")
  private Integer type;
  @Column(name = "open")
  private Integer open;
  @Column(name = "due")
  private Integer due;

  public Assignment() {
    this.course = null;
    this.name = null;
  }

  public Assignment(String course, String name, int type) {
    this.course = course;
    this.name = name;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
