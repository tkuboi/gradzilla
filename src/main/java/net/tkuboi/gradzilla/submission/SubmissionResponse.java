package net.tkuboi.gradzilla.submission;

public class SubmissionResponse {
  private String status;
  private String message;
  private String filePath;
  private Integer score;

  public SubmissionResponse() {
    this.status = "";
    this.message = "";
    this.filePath = "";
    this.score = null;
  }

  public SubmissionResponse(String status, String message, String filePath, Integer score) {
    this.status = status;
    this.message = message;
    this.filePath = filePath;
    this.score = score;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
