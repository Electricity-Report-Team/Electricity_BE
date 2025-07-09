package com.electricity.backend.controller;

import com.electricity.backend.dto.PredictEnergyDto;
import com.electricity.backend.service.PredictService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PredictPowerController {

  private final PredictService predictService;

  public PredictPowerController(PredictService predictService){
    this.predictService = predictService;
  }

  @GetMapping("/api/v1/regions/{regionId}/predicted-consumption")
  public PredictEnergyDto getPredictPower(@PathVariable Long regionId, @RequestParam int predictMonth){
    return predictService.getPredictPower(regionId,predictMonth);
  }

}
