package com.electricity.backend.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Top5IndustryResponseDto {
  private Long regionId;
  private String regionName;
  private List<String> top5Industries;

  public Top5IndustryResponseDto(Long regionId, String regionName, List<String> top5Industries) {
    this.regionId = regionId;
    this.regionName = regionName;
    this.top5Industries = top5Industries;
  }
}
