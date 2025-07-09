package com.electricity.backend.repository;

import com.electricity.backend.entity.Predict;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictEnergyRepository extends JpaRepository<Predict,Long> {
  List<Predict> findByRegion_RegionIdAndPredictMonthOrderByIndustry_IndustryName(Long regionId, int predictMonth);

}
