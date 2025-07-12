package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScenarioDto {

  private String industryName;
  private String solution;
  private Long decrease;
  private String detail;
  private double originalCharge;
  private double reducedCharge;
  private double originalPower;
  private double reducedPower;

  public ScenarioDto(String industryName, String solution,
      Long decrease, String detail, double originalCharge,
      double reducedCharge,double originalPower,
      double reducedPower){
    this.industryName = industryName;
    this.solution = solution;
    this.decrease = decrease;
    this.detail = detail;
    this.originalCharge = originalCharge;
    this.reducedCharge = reducedCharge;
    this.originalPower = originalPower;
    this.reducedPower = reducedPower;

  }


}
