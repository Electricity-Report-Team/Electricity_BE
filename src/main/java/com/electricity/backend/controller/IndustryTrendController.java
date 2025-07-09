package com.electricity.backend.controller;

import com.electricity.backend.dto.IndustryTrendDto;
import com.electricity.backend.service.IndustryTrendService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class IndustryTrendController {

  private final IndustryTrendService industryTrendService;

  public IndustryTrendController(IndustryTrendService industryTrendService){
    this.industryTrendService = industryTrendService;
  }

  @GetMapping("/api/v1/region/{regionId}/industry-trend")
  public List<IndustryTrendDto> getIndustryTrend(@PathVariable Long regionId){
    return industryTrendService.getYearlyTrend(regionId);
  }

}
