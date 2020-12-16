package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.WaterTank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterTankRepository extends JpaRepository<WaterTank, Long> {
}
