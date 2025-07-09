package com.electricity.backend.service;

import com.electricity.backend.dto.Top5IndustryResponseDto;
import com.electricity.backend.entity.RegionAverage;
import com.electricity.backend.repository.RegionAverageRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class Top5IndustryService {
  private final RegionAverageRepository regionAverageRepository;

  public Top5IndustryService(RegionAverageRepository regionAverageRepository) {
    this.regionAverageRepository = regionAverageRepository;
  }

  // Top5 전력사용량 산업 추출
  public Top5IndustryResponseDto getTop5Industries(Long regionId){
    // top5 산업
    List<RegionAverage> top5 = regionAverageRepository
        .findTop5ByRegion_RegionIdOrderByLocalAvgDesc(regionId)
        .stream()
        .limit(5)
        .collect(Collectors.toList());

    // 지역명
    String regionName = top5.get(0).getRegion().getRegionName();

    // 산업명 리스트
    List<String> industryNames = top5.stream()
        .map(ra -> ra.getIndustry().getIndustryName())
        .collect(Collectors.toList());

    return new Top5IndustryResponseDto(regionId,regionName,industryNames);
  }

}
