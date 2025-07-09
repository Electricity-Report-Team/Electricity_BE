package com.electricity.backend.service;

import com.electricity.backend.dto.RegionDto;
import com.electricity.backend.repository.RegionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

  private final RegionRepository regionRepository;

  public RegionService(RegionRepository regionRepository){
    this.regionRepository = regionRepository;
  }

  public List<RegionDto> getAllRegions(){
    return regionRepository.findAll().stream()
        .map(region -> new RegionDto(region.getRegionId(),region.getRegionName()))
        .collect(Collectors.toList());
  }

}
