package net.tkuboi.gradzilla.quarter;

public class QuarterId implements java.io.Serializable {
  private Integer year;
  private String quarter;

  public QuarterId() {
    this.year = null;
    this.quarter = null;
  }

  public QuarterId(Integer year, String quarter) {
    this.year = year;
    this.quarter = quarter;
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
}
