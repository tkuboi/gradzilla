package net.tkuboi.gradzilla.submission;

public class SubmissionResponse {
  private String status;
  private String message;
  private String filePath;

  public SubmissionResponse() {
    this.status = "";
    this.message = "";
    this.filePath = "";
  }

  public SubmissionResponse(String status, String message, String filePath) {
    this.status = status;
    this.message = message;
    this.filePath = filePath;
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
}
