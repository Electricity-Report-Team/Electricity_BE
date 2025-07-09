package com.electricity.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IndustryAvgComparisionDto {
  private String industryName;
  private double localAvg;
  private double nationAvg;
  private double deviationPercent;

}
