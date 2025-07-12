package com.electricity.backend.entity;

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
@Table(name = "Cause")
public class Cause {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int causeId;

  @ManyToOne
  @JoinColumn(name = "industryId")
  private Industry industry;

  private String cause;

  private String solution;

  private Long decrease;

  private String detail;

}
