package com.electricity.backend.service;

import com.electricity.backend.dto.IndustryTrendDto;
import com.electricity.backend.dto.YearlyDataDto;
import com.electricity.backend.repository.IndustryTrendRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class IndustryTrendService {

  private final IndustryTrendRepository industryTrendRepository;

  public IndustryTrendService(IndustryTrendRepository industryTrendRepository){
    this.industryTrendRepository = industryTrendRepository;
  }

  public List<IndustryTrendDto> getYearlyTrend(Long regionId){
    List<Object[]> rawData = industryTrendRepository.findYearlyAvgPowerGroupedByIndustry(regionId);
    Map<String,List<YearlyDataDto>> response = new LinkedHashMap<>();

    for(Object[] row : rawData){
      String industryName = (String) row[0];
      int year = (int) row[1];
      double avgPower = ((Number) row[2]).doubleValue();

      response.computeIfAbsent(industryName,k -> new ArrayList<>()).add(new YearlyDataDto(year,avgPower));
    }
    List<IndustryTrendDto> result = new ArrayList<>();
    for(Map.Entry<String,List<YearlyDataDto>> entry : response.entrySet()){
      result.add(new IndustryTrendDto(entry.getKey(),entry.getValue()));
    }
    return result;
  }

}
