package net.tkuboi.gradzilla.quarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuarterController {
  private final QuarterRepository quarterRepository;

  @Autowired
  public QuarterController(QuarterRepository quarterRepository) {
    this.quarterRepository = quarterRepository;
  }

  @GetMapping("/current-quarter")
  public Quarter getCurrentQuarter() {
    return this.quarterRepository.findQuarterByCurrentTrue();
  }
}
