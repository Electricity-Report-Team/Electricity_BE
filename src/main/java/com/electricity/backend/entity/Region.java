package com.electricity.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Region")
public class Region {
  @Id
  private Long regionId;

  private String regionName;

}
