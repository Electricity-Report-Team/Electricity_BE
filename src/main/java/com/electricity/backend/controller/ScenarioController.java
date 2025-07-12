package com.electricity.backend.controller;

import com.electricity.backend.dto.ScenarioDto;
import com.electricity.backend.service.ScenarioService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ScenarioController {

  private final ScenarioService scenarioService;

  public ScenarioController(ScenarioService scenarioService){
    this.scenarioService = scenarioService;
  }

  @GetMapping("/api/v1/regions/{regionId}/scenario")
  public List<ScenarioDto> getScenario(@PathVariable Long regionId){
    return scenarioService.getScenario(regionId);
  }

}
