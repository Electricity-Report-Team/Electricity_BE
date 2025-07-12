package com.electricity.backend.repository;

import com.electricity.backend.dto.ScenarioDto;
import com.electricity.backend.entity.Cause;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CauseRepository extends JpaRepository<Cause,Integer> {
  Optional<Cause> findByIndustry_IndustryId(Long industryId);

  @Query("""
    SELECT new com.electricity.backend.dto.ScenarioDto(
        c.industry.industryName,
        c.solution,
        c.decrease,
        c.detail,
        p.predictAvgCharge,
        ROUND(p.predictAvgCharge * (1 - c.decrease / 100.0)),
        p.predictAvgPower,
        ROUND(p.predictAvgPower * (1 - c.decrease / 100.0))
    )
    FROM Cause c
    JOIN RegionIndustryAvgPrediction p
      ON c.industry.industryId = p.industry.industryId
    WHERE p.region.regionId = :regionId
""")
  List<ScenarioDto> findScenariosByRegion(@Param("regionId") Long regionId);

}
