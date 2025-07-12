package com.electricity.backend.controller;

import com.electricity.backend.dto.CauseDto;
import com.electricity.backend.service.CauseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CauseController {

  private final CauseService causeService;

  public CauseController(CauseService causeService){
    this.causeService = causeService;
  }

  @GetMapping("/api/v1/industries/{industryId}/cause")
  public CauseDto getCause(@PathVariable Long industryId){
    return causeService.getCause(industryId);
  }

}
