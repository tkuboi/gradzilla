package net.tkuboi.gradzilla.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@ConfigurationProperties(prefix="file.upload-dir")
public class FileStorageProperties {
  private String dev;
  private String prod;


  public String getUploadDir() {
    if (new File(this.prod).exists()) {
      return this.prod;
    }
    return this.dev;
  }

  public String getDev() {
    return dev;
  }

  public void setDev(String dev) {
    this.dev = dev;
  }

  public String getProd() {
    return prod;
  }

  public void setProd(String prod) {
    this.prod = prod;
  }
}
