package com.electricity.backend.repository;

import com.electricity.backend.entity.Industry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry,Long> {
  List<Industry> findAll();

}
