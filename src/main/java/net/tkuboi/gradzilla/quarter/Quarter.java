package net.tkuboi.gradzilla.quarter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Quarter")
public class Quarter {
  @EmbeddedId
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "year", nullable = false)),
    @AttributeOverride(name = "quarter", column = @Column(name = "quarter", nullable = false))})
  private QuarterId id;

  @Column(name="current")
  private Boolean current;

  public Quarter() {
    this.id = null;
    this.current = false;
  }

  public Quarter(QuarterId id, Boolean current) {
    this.id = id;
    this.current = current;
  }

  public Quarter(String quarter, Integer year, Boolean current) {
    this.id = new QuarterId(year, quarter);
    this.current = current;
  }

  public Boolean getCurrent() {
    return current;
  }

  public void setCurrent(Boolean current) {
    this.current = current;
  }

  public QuarterId getId() {
    return id;
  }

  public void setId(QuarterId id) {
    this.id = id;
  }
}
