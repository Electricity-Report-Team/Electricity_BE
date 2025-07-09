package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YearlyDataDto {

  private int year;
  private double avgPower;

  public YearlyDataDto(int year, double avgPower){
    this.year = year;
    this.avgPower = avgPower;
  }
}
