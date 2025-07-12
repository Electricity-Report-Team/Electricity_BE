package com.electricity.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Top3IndustryDto {

  private int rank;
  private Long industryId;
  private String industryName;
  private double predictAvgPower;
  private double predictAvgCharge;


}
