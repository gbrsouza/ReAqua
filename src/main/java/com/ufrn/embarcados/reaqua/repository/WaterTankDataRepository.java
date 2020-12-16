package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.WaterTankData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WaterTankDataRepository extends JpaRepository<WaterTankData, Long> {
    @Query(value="select * from water_tank_data e where e.timestamp between :di and :df", nativeQuery = true)
    List<WaterTankData> findByDateRange(@Param("di") LocalDate startDate,
                                        @Param("df") LocalDate finalDate);

    @Query(value="select * from water_tank_data e where e.water_tank_id = :tid", nativeQuery = true)
    List<WaterTankData> findByWaterTank(@Param("tid") Long tankId);
}
