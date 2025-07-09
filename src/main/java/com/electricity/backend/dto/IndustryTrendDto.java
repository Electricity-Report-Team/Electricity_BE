package com.electricity.backend.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryTrendDto {

  private String industryName;
  private List<YearlyDataDto> yearlyData;

  public IndustryTrendDto(String industryName, List<YearlyDataDto> yearlyData){
    this. industryName = industryName;
    this.yearlyData = yearlyData;
  }
}
