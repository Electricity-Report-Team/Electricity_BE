package com.electricity.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "region_industry_avg_prediction")
public class RegionIndustryAvgPrediction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "regionId")
  private Region region;

  @ManyToOne
  @JoinColumn(name = "industryId")
  private Industry industry;

  @Column(name = "predictAvgPower")
  private double predictAvgPower;

  @Column(name = "predictAvgCharge")
  private double predictAvgCharge;

}
