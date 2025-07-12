package com.electricity.backend.service;

import com.electricity.backend.dto.ScenarioDto;
import com.electricity.backend.repository.CauseRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

  private final CauseRepository causeRepository;

  public ScenarioService(CauseRepository causeRepository){
    this.causeRepository = causeRepository;
  }

  public List<ScenarioDto> getScenario(Long regionId){
    return causeRepository.findScenariosByRegion(regionId);
  }

}
