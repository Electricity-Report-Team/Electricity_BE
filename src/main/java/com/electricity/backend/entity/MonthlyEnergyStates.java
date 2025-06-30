package com.electricity.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MonthlyEnergyStates")
public class MonthlyEnergyStates {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="statesId")
  private Long statesId;

  @ManyToOne
  @JoinColumn(name = "regionId")
  private Region region;

  @ManyToOne
  @JoinColumn(name = "industryId")
  private Industry industry;

  @Column(name = "year")
  private int year;

  @Column(name = "month")
  private int month;

  @Column(name = "power")
  private BigDecimal power;

  @Column(name = "charge")
  private BigDecimal charge;

}
