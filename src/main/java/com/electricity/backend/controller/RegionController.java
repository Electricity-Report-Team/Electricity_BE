package com.electricity.backend.controller;

import com.electricity.backend.dto.RegionDto;
import com.electricity.backend.service.RegionService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class RegionController {

  private final RegionService regionService;

  public RegionController(RegionService regionService){
    this.regionService = regionService;
  }

  @GetMapping("/api/v1/region")
  public List<RegionDto> getAllRegions(){
    return regionService.getAllRegions();
  }

}
