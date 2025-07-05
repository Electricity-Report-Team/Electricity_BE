package com.electricity.backend.controller;

import com.electricity.backend.dto.MonthlyChargeStatesDto;
import com.electricity.backend.dto.MonthlyConsumptionResponseDto;
import com.electricity.backend.service.MonthlyEnergyStatesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/regions")
public class MonthlyEnergyStatesController {

  private final MonthlyEnergyStatesService monthlyEnergyStatesService;

  public MonthlyEnergyStatesController(MonthlyEnergyStatesService monthlyEnergyStates) {
    this.monthlyEnergyStatesService = monthlyEnergyStates;
  }

  @GetMapping("/{regionId}/monthly-consumption")
  public MonthlyConsumptionResponseDto getMonthlyEnergyStates(@PathVariable Long regionId, @RequestParam int year, @RequestParam int month) {
    return monthlyEnergyStatesService.getMonthlyEnergyStates(regionId, year, month);
  }

  @GetMapping("/{regionId}/monthly-charge")
  public MonthlyChargeStatesDto getMonthlyChargeStates(@PathVariable Long regionId, @RequestParam int year, @RequestParam int month){
    return monthlyEnergyStatesService.getMonthlyChargeStates(regionId, year, month);
  }


}
