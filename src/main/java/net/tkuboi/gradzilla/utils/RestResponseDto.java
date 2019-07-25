package net.tkuboi.gradzilla.utils;

public class RestResponseDto {
  public enum Status {
    SUCCESS,
    ERROR
  }

  private Integer exitCode;
  private String message;
  private String status;
  private Object object;

  public RestResponseDto() {
    this.exitCode = null;
    this.message = null;
    this.status = null;
    this.object = null;
  }

  public RestResponseDto(Integer exitCode, String message, String status, Object object) {
    this.exitCode = exitCode;
    this.message = message;
    this.status = status;
    this.object = object;
  }

  public Integer getExitCode() {
    return exitCode;
  }

  public void setExitCode(Integer exitCode) {
    this.exitCode = exitCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

}
