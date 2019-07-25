package net.tkuboi.gradzilla.assignment;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
  private Timestamp open;
  @Column(name = "due")
  private Timestamp due;

  public Assignment() {
    this.course = null;
    this.name = null;
    this.type = null;
    this.open = null;
    this.due = null;
  }

  public Assignment(String course, String name, Integer type, Timestamp open, Timestamp due) {
    this.course = course;
    this.name = name;
    this.type = type;
    this.open = open;
    this.due = due;
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

  public Timestamp getOpen() {
    return open;
  }

  /*public void setOpen(Timestamp open) {
    this.open = open;
  }*/

  public void setOpen(Long open) {
    this.open  = new Timestamp(open);
  }

  public Timestamp getDue() {
    return due;
  }

  /*public void setDue(Timestamp due) {
    this.due = due;
  }*/

  public void setDue(Long due) {
    this.due = new Timestamp(due);
  }
}
