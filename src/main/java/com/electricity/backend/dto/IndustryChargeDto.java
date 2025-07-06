package com.electricity.backend.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class IndustryChargeDto {
  private String industryName;
  private BigDecimal charge;
}
