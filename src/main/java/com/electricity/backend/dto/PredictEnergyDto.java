package com.electricity.backend.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictEnergyDto {

  private String regionName;
  private List<IndustryPredictPowerDto> industryPower;

  public PredictEnergyDto(String regionName, List<IndustryPredictPowerDto> industryPower){
    this.regionName = regionName;
    this.industryPower = industryPower;
  }

}
