package com.electricity.backend.repository;

import com.electricity.backend.entity.RegionAverage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionAverageRepository extends JpaRepository<RegionAverage,Long> {
  List<RegionAverage> findTop5ByRegion_RegionIdOrderByLocalAvgDesc(Long regionId);
  List<RegionAverage> findByRegion_RegionId(Long regionId);
}
