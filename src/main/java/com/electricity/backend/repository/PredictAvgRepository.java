package com.electricity.backend.repository;

import com.electricity.backend.entity.RegionIndustryAvgPrediction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PredictAvgRepository extends JpaRepository<RegionIndustryAvgPrediction,Long> {
  List<RegionIndustryAvgPrediction> findByRegion_RegionIdOrderByPredictAvgPowerDesc(Long regionId);
  @Query(value = """
        SELECT 
            regionId AS regionId,
            SUM(predictAvgPower) AS totalPower,
            SUM(predictAvgCharge) AS totalCharge
        FROM region_industry_avg_prediction
        WHERE regionId = :regionId
        GROUP BY regionId
        """, nativeQuery = true)
  List<Object[]> findTotalPredictionByRegion_RegionId(@Param("regionId") Long regionId);

}
