package net.tkuboi.gradzilla.grader;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Grader")
public class Grader {
  public enum Type {
    TEST,
    LINT
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = IDENTITY)
  private int id;
  @Column(name = "assignment")
  private int assignment;
  @Column(name = "seq")
  private int seq;
  @Column(name = "filePath")
  private String filePath;
  @Column(name = "program")
  private String program;
  @Column(name = "args")
  private String args;
  @Column(name = "copy")
  private Integer copy;
  @Column(name = "type")
  private String type;

  public Grader() {}

  public Grader(int assignment, int seq, String filePath, String program, String args, Integer copy, String type) {
    this.assignment = assignment;
    this.seq = seq;
    this.filePath = filePath;
    this.program = program;
    this.args = args;
    this.copy = copy;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAssignment() {
    return assignment;
  }

  public void setAssignment(int assignment) {
    this.assignment = assignment;
  }

  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  public String getArgs() {
    return args;
  }

  public void setArgs(String args) {
    this.args = args;
  }

  public Integer getCopy() {
    return copy;
  }

  public void setCopy(Integer copy) {
    this.copy = copy;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
