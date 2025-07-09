package com.electricity.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "NationAverage")
public class NationAverage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "nationAvgId")
  private Long nationAvgId;

  @ManyToOne
  @JoinColumn(name = "industryId")
  private Industry industry;

  @Column(name = "nationalAvg")
  private double nationalAvg;


}
