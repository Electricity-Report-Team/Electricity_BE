package com.electricity.backend.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryPredictPowerDto {

  private String industryName;
  private BigDecimal predictPower;

  public IndustryPredictPowerDto(String industryName, BigDecimal predictPower){
    this.industryName = industryName;
    this.predictPower = predictPower;
  }

}
