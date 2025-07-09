package com.electricity.backend.repository;

import com.electricity.backend.entity.MonthlyEnergyStates;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IndustryTrendRepository extends JpaRepository<MonthlyEnergyStates, Long> {

  @Query("SELECT m.industry.industryName, m.year, AVG(m.power) " +
      "FROM MonthlyEnergyStates m " +
      "WHERE m.region.regionId = :regionId AND " +
      "((m.year = 2015 AND m.month BETWEEN 4 AND 12) OR " +
      " (m.year BETWEEN 2016 AND 2024) OR " +
      " (m.year = 2025 AND m.month BETWEEN 1 AND 3)) " +
      "GROUP BY m.industry.industryName, m.year " +
      "ORDER BY m.industry.industryName, m.year")
  List<Object[]> findYearlyAvgPowerGroupedByIndustry(Long regionId);
}
