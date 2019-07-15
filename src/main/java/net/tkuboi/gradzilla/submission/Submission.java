package net.tkuboi.gradzilla.submission;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Submission")
public class Submission {
  public static enum Status {
    SUBMITTED("Submitted"),
    GRADING("Grading"),
    GRADED("Graded");

    private String text;

    Status(String text) {
      this.text = text;
    }

    public String toString() {
      return text;
    }
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = IDENTITY)
  private int id;
  @Column(name = "assignment")
  private int assignment;
  @Column(name = "user")
  private String user;
  @Column(name = "submissionTime")
  private Timestamp submissionTime;
  @Column(name = "status")
  private String status;
  @Column(name = "score")
  private Integer score;
  @Column(name = "filePath")
  private String filePath;
  @Column(name = "resultFile")
  private String resultFile;
  @Column(name = "result")
  private String result;

  public Submission() {
    this.submissionTime = null;
    this.status = null;
    this.score = null;
    this.resultFile = null;
    this.result = null;
  }

  public Submission(int assignment, String user, String filePath) {
    this.assignment = assignment;
    this.user = user;
    this.filePath = filePath;
    this.submissionTime = null;
    this.status = null;
    this.score = null;
    this.resultFile = null;
    this.result = null;
  }

  public Submission(int assignment, String user, Timestamp timestamp, String filePath) {
    this.assignment = assignment;
    this.user = user;
    this.submissionTime = timestamp;
    this.filePath = filePath;
    this.status = null;
    this.score = null;
    this.resultFile = null;
    this.result = null;
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

  public Timestamp getSubmissionTime() {
    return submissionTime;
  }

  public void setSubmissionTime(Timestamp submissionTime) {
    this.submissionTime = submissionTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getResultFile() {
    return resultFile;
  }

  public void setResultFile(String resultFile) {
    this.resultFile = resultFile;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
