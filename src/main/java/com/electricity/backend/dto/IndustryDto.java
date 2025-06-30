package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryDto {
  private String industryName;

  public IndustryDto(String industryName) {
    this.industryName = industryName;
  }

}
