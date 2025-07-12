package com.electricity.backend.service;

import com.electricity.backend.dto.PredictTotalDto;
import com.electricity.backend.dto.Top3IndustryDto;
import com.electricity.backend.entity.RegionIndustryAvgPrediction;
import com.electricity.backend.repository.PredictAvgRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PredictAvgService {

  private final PredictAvgRepository predictAvgRepository;

  public PredictAvgService(PredictAvgRepository predictAvgRepository){
    this.predictAvgRepository = predictAvgRepository;
  }

  public List<Top3IndustryDto> getTop3Industry(Long regionId){
    List<RegionIndustryAvgPrediction> list = predictAvgRepository.findByRegion_RegionIdOrderByPredictAvgPowerDesc(regionId);

    return list.stream().filter(item -> item.getIndustry() != null) .limit(3)
        .map((item) ->new Top3IndustryDto(
            list.indexOf(item)+1,
            item.getIndustry().getIndustryId(),
            item.getIndustry().getIndustryName(),
            item.getPredictAvgPower(),
            item.getPredictAvgCharge()
        )).toList();
  }

  // 총 예측 전력, 전기세 합
  public PredictTotalDto getTotal(Long regionId){
    List<Object[]> results = predictAvgRepository.findTotalPredictionByRegion_RegionId(regionId);

    if (results.isEmpty()) {
      return new PredictTotalDto(regionId, 0.0, 0.0);
    }

    Object[] row = results.get(0);
    Double predictTotalPower = row[1] != null ? ((Number) row[1]).doubleValue() : 0.0;
    Double predictTotalCharge = row[2] != null ? ((Number) row[2]).doubleValue() : 0.0;

    return new PredictTotalDto(regionId, predictTotalPower, predictTotalCharge);
  }
  }


