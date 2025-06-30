package com.electricity.backend.repository;

import com.electricity.backend.entity.MonthlyEnergyStates;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyEnergyStatesRepository extends JpaRepository<MonthlyEnergyStates, Long> {
  List<MonthlyEnergyStates> findByRegion_RegionIdAndYearAndMonthOrderByIndustry_IndustryName(Long regionId, int year, int month);

}
