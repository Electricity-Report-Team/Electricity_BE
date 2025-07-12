package com.electricity.backend.service;

import com.electricity.backend.dto.CauseDto;
import com.electricity.backend.entity.Cause;
import com.electricity.backend.entity.Industry;
import com.electricity.backend.repository.CauseRepository;
import com.electricity.backend.repository.IndustryRepository;
import org.springframework.stereotype.Service;

@Service
public class CauseService {

  private final CauseRepository causeRepository;
  private final IndustryRepository industryRepository;

  public CauseService(CauseRepository causeRepository, IndustryRepository industryRepository){
    this.causeRepository = causeRepository;
    this.industryRepository = industryRepository;
  }

  public CauseDto getCause(Long industryId){
    Cause cause = causeRepository.findByIndustry_IndustryId(industryId)
        .orElseThrow(()->new IllegalArgumentException("해당 산업 원인 정보 없음"));
    String industryName = industryRepository.findIndustryNameByIndustryId(industryId)
        .map(Industry::getIndustryName).orElse("알수없음");
    return new CauseDto(industryId,industryName,cause.getCause());
  }
}
