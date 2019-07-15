package net.tkuboi.gradzilla.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
  @Id
  @Column(name = "name")
  private String name;
  @Column(name = "shortDescription")
  private String shortDescription;
  @Column(name = "description")
  private String description;

  public Course() {
    this.name = null;
    this.shortDescription = null;
    this.description = null;
  }

  public Course(String name, String shortDescription, String description) {
    this.name = name;
    this.shortDescription = shortDescription;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
