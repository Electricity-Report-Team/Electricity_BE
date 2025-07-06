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
@Table(name = "region_average")
public class RegionAverage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "avgId")
  private Long avgId;

  @ManyToOne
  @JoinColumn(name = "regionId")
  private Region region;

  @ManyToOne
  @JoinColumn(name = "industryId")
  private Industry industry;

  @Column(name = "localAvg")
  private float localAvg;

  @Column(name = "periodStart")
  private Integer periodStart;

  @Column(name = "periodEnd")
  private Integer periodEnd;

}
