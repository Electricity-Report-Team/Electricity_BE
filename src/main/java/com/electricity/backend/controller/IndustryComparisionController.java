package com.electricity.backend.controller;

import com.electricity.backend.dto.IndustryAvgComparisionDto;
import com.electricity.backend.service.IndustryAvgComparisionService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class IndustryComparisionController {

  private final IndustryAvgComparisionService industryAvgComparisionService;

  public IndustryComparisionController(IndustryAvgComparisionService industryAvgComparisionService){
    this.industryAvgComparisionService = industryAvgComparisionService;
  }

  @GetMapping("/api/v1/region/{regionId}/industry-avg-comparison")
  public List<IndustryAvgComparisionDto> getIndustryAvgComparison(@PathVariable Long regionId){
    return industryAvgComparisionService.getIndustryComparision(regionId);
  }

}
