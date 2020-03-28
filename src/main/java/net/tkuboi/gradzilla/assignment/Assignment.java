package net.tkuboi.gradzilla.assignment;


import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Year;

import static javax.persistence.GenerationType.IDENTITY;
import static net.tkuboi.gradzilla.assignment.AssignmentRepository.QUERY_FIND_USER_ASSIGNMENTS;

@Entity
@Table(name = "Assignment")
@SqlResultSetMapping(
  // the result mapping used by all NamedNativeQuery annotations which attempt to classify a person
  name = "assignmentDTOMapping",
  classes = {
    @ConstructorResult(
      targetClass = AssignmentDTO.class,
      columns = {
        @ColumnResult(name = "a.id", type = Integer.class),
        @ColumnResult(name = "a.course", type = String.class),
        @ColumnResult(name = "a.name", type = String.class),
        @ColumnResult(name = "a.open", type = LocalDateTime.class),
        @ColumnResult(name = "a.due", type = LocalDateTime.class),
        @ColumnResult(name = "a.instruction_link", type = String.class),
        @ColumnResult(name = "a.instruction", type = Clob.class),
        @ColumnResult(name = "a.year", type = BigInteger.class),
        @ColumnResult(name = "a.quarter", type = String.class),
        @ColumnResult(name = "a.type", type = Integer.class),
        @ColumnResult(name = "type_name", type = String.class),
        @ColumnResult(name = "g.user", type = String.class),
        @ColumnResult(name = "grade", type = Integer.class)
      }
    )
  }
)
@NamedNativeQueries({
  @NamedNativeQuery(name = "Assignment.findAllWithGradeByCourseAndCurrentEnrollmentAndUser",
    query = QUERY_FIND_USER_ASSIGNMENTS,
    resultSetMapping = "assignmentDTOMapping")
})
public class Assignment {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "course")
  private String course;
  @Column(name="year")
  private Integer year;
  @Column(name="quarter")
  private String quarter;
  @Column(name = "name")
  private String name;
  @Column(name = "type")
  private Integer type;
  @Column(name = "open")
  private Timestamp open;
  @Column(name = "due")
  private Timestamp due;
  @Column(name = "instruction")
  private String instruction;
  @Column(name = "instruction_link")
  private String instructionLink;

  public Assignment() {
    this.course = null;
    this.year = null;
    this.quarter = null;
    this.name = null;
    this.type = null;
    this.open = null;
    this.due = null;
  }

  public Assignment(String course, Integer year, String quarter, String name,
                    Integer type, Timestamp open, Timestamp due) {
    this.course = course;
    this.year = year;
    this.quarter = quarter;
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

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public String getInstructionLink() {
    return instructionLink;
  }

  public void setInstructionLink(String instructionLink) {
    this.instructionLink = instructionLink;
  }
}
