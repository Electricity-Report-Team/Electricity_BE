package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictTotalDto {

  private Long regionId;
  private Double predictTotalPower;
  private Double predictTotalCharge;

  public PredictTotalDto(Long regionId, Double predictTotalPower, Double predictTotalCharge){
    this.regionId = regionId;
    this.predictTotalPower = predictTotalPower;
    this.predictTotalCharge = predictTotalCharge;
  }

}
