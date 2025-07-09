package com.electricity.backend.service;

import com.electricity.backend.dto.IndustryAvgComparisionDto;
import com.electricity.backend.entity.Industry;
import com.electricity.backend.entity.NationAverage;
import com.electricity.backend.entity.RegionAverage;
import com.electricity.backend.repository.IndustryRepository;
import com.electricity.backend.repository.NationAverageRepository;
import com.electricity.backend.repository.RegionAverageRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class IndustryAvgComparisionService {

  private final RegionAverageRepository regionAverageRepository;
  private final NationAverageRepository nationAverageRepository;
  private final IndustryRepository industryRepository;

  public IndustryAvgComparisionService(RegionAverageRepository regionAverageRepository, NationAverageRepository nationAverageRepository, IndustryRepository industryRepository){
    this.regionAverageRepository = regionAverageRepository;
    this.nationAverageRepository = nationAverageRepository;
    this.industryRepository = industryRepository;
  }

  public List<IndustryAvgComparisionDto> getIndustryComparision(Long regionId){
    List<Industry> industries = industryRepository.findAll();
    List<RegionAverage> regionAverages = regionAverageRepository.findByRegion_RegionId(regionId);
    List<NationAverage> nationAverages = nationAverageRepository.findAll();

    Map<Long,Double> nationalAvgMap = nationAverages.stream()
        .collect(Collectors.toMap(n -> n.getIndustry().getIndustryId(), NationAverage::getNationalAvg));

    Map<Long, String> industryNameMap = industries.stream()
        .collect(Collectors.toMap(Industry::getIndustryId, Industry::getIndustryName));

    return regionAverages.stream().map(ra ->{
      Long industryId = ra.getIndustry().getIndustryId();
      String industryName = industryNameMap.getOrDefault(industryId,"Unkown");
      double localAvg = ra.getLocalAvg();
      double nationalAvg = nationalAvgMap.getOrDefault(industryId,0.0);
      double deviation = nationalAvg == 0 ? 0 : Math.round(((localAvg - nationalAvg) / nationalAvg * 100) * 10) / 10.0;

      return new IndustryAvgComparisionDto(industryName, localAvg, nationalAvg, deviation);
    }).collect(Collectors.toList());
  }

}
