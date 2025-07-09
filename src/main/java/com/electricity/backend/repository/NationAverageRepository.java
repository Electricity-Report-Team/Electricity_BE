package com.electricity.backend.repository;

import com.electricity.backend.entity.NationAverage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NationAverageRepository extends JpaRepository<NationAverage,Long> {
  List<NationAverage> findAll();

}
