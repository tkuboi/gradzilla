package net.tkuboi.gradzilla.quarter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuarterRepository extends JpaRepository<Quarter, Integer> {
  Quarter findQuarterByCurrentTrue();
  Quarter findQuarterById(QuarterId id);
}
