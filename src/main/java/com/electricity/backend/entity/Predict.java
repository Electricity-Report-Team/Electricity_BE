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
@Table(name = "Predict")
public class Predict {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "predictId")
  private Long predictId;

  @ManyToOne
  @JoinColumn(name = "regionId")
  private Region region;

  @ManyToOne
  @JoinColumn(name = "industryId")
  private Industry industry;

  @Column(name = "predictMonth")
  private int predictMonth;

  @Column(name = "predictPower")
  private double predictPower;

  @Column(name = "predictCharge")
  private double predictCharge;


}
