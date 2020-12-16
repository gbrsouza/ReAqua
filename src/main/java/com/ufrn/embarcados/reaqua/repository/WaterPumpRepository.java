package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.WaterPump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaterPumpRepository extends JpaRepository<WaterPump, Long> {
    @Query(value="select * from water_pump e where e.water_tank_id = :tid", nativeQuery = true)
    void findByWaterTankId(@Param("tid") Long tankId);
}
