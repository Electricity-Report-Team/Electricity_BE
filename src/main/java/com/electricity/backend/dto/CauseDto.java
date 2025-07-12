package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CauseDto {

  private Long industryId;
  private String industryName;
  private String cause;

  public CauseDto(Long industryId, String industryName, String cause){
    this.industryId = industryId;
    this.industryName = industryName;
    this.cause = cause;
  }


}
