package net.tkuboi.gradzilla.grader;

public class GraderResultDto {
  private String output;
  private Integer score;

  public GraderResultDto() {
    this.output = null;
    this.score = null;
  }

  public GraderResultDto(String output, Integer score) {
    this.output = output;
    this.score = score;
  }
  public String getResult() {
    return this.output;
  }

  public void setResult(String output) {
    this.output = output;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
