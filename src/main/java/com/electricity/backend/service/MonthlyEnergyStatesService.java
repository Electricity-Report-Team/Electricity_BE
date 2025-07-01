package com.electricity.backend.service;

import com.electricity.backend.dto.IndustryPowerDto;
import com.electricity.backend.dto.MonthlyConsumptionResponseDto;
import com.electricity.backend.entity.MonthlyEnergyStates;
import com.electricity.backend.repository.MonthlyEnergyStatesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MonthlyEnergyStatesService {
  private final MonthlyEnergyStatesRepository monthlyEnergyStates;

  public MonthlyEnergyStatesService(MonthlyEnergyStatesRepository monthlyEnergyStates) {
    this.monthlyEnergyStates = monthlyEnergyStates;
  }

  // 지역별(년,월에 해당하는) 전력 소비량 추출
  public MonthlyConsumptionResponseDto getMonthlyEnergyStates(Long regionId, int year, int month){
    //지역에 해당하는 전력 소비량 담은 객체
    List<MonthlyEnergyStates> response = monthlyEnergyStates.findByRegion_RegionIdAndYearAndMonthOrderByIndustry_IndustryName(regionId, year, month);
    // 지역 이름 추출
    String regionName = response.get(0).getRegion().getRegionName();
    // 산업명, 전력 사용량 추출
    List<IndustryPowerDto> industryPowerDtos = response
        .stream().map(states -> new IndustryPowerDto(
            states.getIndustry().getIndustryName(),
            states.getPower())).collect(Collectors.toList());

    return new MonthlyConsumptionResponseDto(regionName, industryPowerDtos);
  }



}
