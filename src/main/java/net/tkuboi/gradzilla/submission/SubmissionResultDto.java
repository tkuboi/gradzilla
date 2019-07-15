package net.tkuboi.gradzilla.submission;

public class SubmissionResultDto {
  private String result;
  private Integer score;

  public SubmissionResultDto() {
    this.result = null;
    this.score = null;
  }

  public SubmissionResultDto(String result, Integer score) {
    this.result = result;
    this.score = score;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
