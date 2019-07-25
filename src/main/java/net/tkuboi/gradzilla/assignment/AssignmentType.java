package net.tkuboi.gradzilla.assignment;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "AssignmentType")
public class AssignmentType {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "name")
  private String name;

  public AssignmentType() {
    this.name = null;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
