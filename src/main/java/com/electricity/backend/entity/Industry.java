package com.electricity.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Industry")
public class Industry {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long industryId;

  private String industryName;

}
