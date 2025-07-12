package com.electricity.backend.controller;

import com.electricity.backend.dto.PredictTotalDto;
import com.electricity.backend.dto.Top3IndustryDto;
import com.electricity.backend.service.PredictAvgService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PredictAvgController {

  private final PredictAvgService predictAvgService;

  public PredictAvgController(PredictAvgService predictAvgService){
    this.predictAvgService = predictAvgService;
  }

  @GetMapping("/api/v1/scenario/{regionId}/Top3")
  public List<Top3IndustryDto> getTop3Industry(@PathVariable Long regionId){
    return predictAvgService.getTop3Industry(regionId);
  }

  // 총합
  @GetMapping("/api/v1/region/{regionId}/total-prediction")
  public PredictTotalDto getTotal(@PathVariable Long regionId){
    return predictAvgService.getTotal(regionId);
  }

}
