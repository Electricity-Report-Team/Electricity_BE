package com.electricity.backend.controller;

import com.electricity.backend.dto.Top5IndustryResponseDto;
import com.electricity.backend.service.Top5IndustryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/regions")
public class Top5IndustryController {

  private final Top5IndustryService top5IndustryService;

  public Top5IndustryController(Top5IndustryService top5IndustryService) {
    this.top5IndustryService = top5IndustryService;
  }
  //top5 전력 소비량 조회 API
  @GetMapping("/{regionId}/top5")
  public Top5IndustryResponseDto getTop5Industry(@PathVariable long regionId) {
    return top5IndustryService.getTop5Industries(regionId);
  }

}
