package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDto {

  private Long regionId;
  private String regionName;

  public RegionDto(Long regionId, String regionName){
    this.regionId = regionId;
    this.regionName = regionName;
  }

}
