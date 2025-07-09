package com.electricity.backend.service;

import com.electricity.backend.dto.IndustryPredictPowerDto;
import com.electricity.backend.dto.PredictEnergyDto;
import com.electricity.backend.entity.Predict;
import com.electricity.backend.repository.PredictEnergyRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PredictService {

  private final PredictEnergyRepository predictEnergyRepository;

  public PredictService(PredictEnergyRepository predictEnergyRepository){
    this.predictEnergyRepository = predictEnergyRepository;
  }

  // 2026년 예측 전력소비량
  public PredictEnergyDto getPredictPower(Long regionId, int predictMonth){
    //1. 레포지토리 호출
    List<Predict> response = predictEnergyRepository.findByRegion_RegionIdAndPredictMonthOrderByIndustry_IndustryName(regionId, predictMonth);
    if (response.isEmpty()) {
      return new PredictEnergyDto("데이터 없음", List.of());
    }
    //2.지역명 추출
    String regionName = response.get(0).getRegion().getRegionName();
    List<IndustryPredictPowerDto> predictPowerDto = response
        .stream().map(states -> new IndustryPredictPowerDto(
            states.getIndustry().getIndustryName(),
            BigDecimal.valueOf(states.getPredictPower())
        )).collect(Collectors.toList());
    return new PredictEnergyDto(regionName,predictPowerDto);
  }

  //2026년 예측 전기세

}
