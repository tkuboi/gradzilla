package net.tkuboi.gradzilla.assignment;

import javax.persistence.Column;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Year;

public class AssignmentDTO {
  private Integer id;
  private String course;
  private String name;
  private Timestamp open;
  private Timestamp due;
  private String instructionLink;
  private String instruction;
  private Integer year;
  private String quarter;
  private String type;
  private String user;
  private Integer grade;

  public AssignmentDTO() {
    this.id = null;
    this.course = null;
    this.name = null;
    this.open = null;
    this.due = null;
    this.instructionLink = null;
    this.instruction = null;
    this.year = null;
    this.quarter = null;
    this.type = null;
    this.user = null;
    this.grade = null;
  }

  public AssignmentDTO(Integer id,
                       String course,
                       String name,
                       LocalDateTime open,
                       LocalDateTime due,
                       String instructionLink,
                       Clob instruction,
                       BigInteger year,
                       String quarter,
                       Integer type,
                       String typeName,
                       String user,
                       Integer grade) {
    this.id = id;
    this.course = course;
    this.name = name;
    this.open = Timestamp.valueOf(open);
    this.due = Timestamp.valueOf(due);
    this.instructionLink = instructionLink;
    this.year = year.intValue();
    this.quarter = quarter;
    this.type = typeName;
    this.user = user;
    this.grade = grade;
    try {
      if (instruction != null) {
        Long len = instruction.length();
        if (len > Long.valueOf(Integer.MAX_VALUE)) {
          len = Long.valueOf(Integer.MAX_VALUE);
        }
        this.instruction = instruction.getSubString(0, len.intValue());
      } else {
        this.instruction = null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Timestamp getOpen() {
    return open;
  }

  public void setOpen(Timestamp open) {
    this.open = open;
  }

  public Timestamp getDue() {
    return due;
  }

  public void setDue(Timestamp due) {
    this.due = due;
  }

  public String getInstructionLink() {
    return instructionLink;
  }

  public void setInstructionLink(String instructionLink) {
    this.instructionLink = instructionLink;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }
}
