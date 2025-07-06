package com.electricity.backend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MonthlyChargeStatesDto {
  private String regionName;
  private List<IndustryChargeDto> industriesCharge;
}
