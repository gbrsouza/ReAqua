package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.WaterTankData;
import com.ufrn.embarcados.reaqua.model.WaterTank;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface WaterTankDataRepository extends JpaRepository<WaterTankData, Long> {

    // Method mantained for compatibility
//    @Query("select w from WaterTankData w where w.waterTank = :waterTank and w.timestamp < :endDate and w.timestamp > :startDate order by timestamp")
//    List<WaterTankData> findByWaterTankAndDate(@Param("waterTank") WaterTank waterTank, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<WaterTankData> findByWaterTankAndTimestampAfterAndTimestampBeforeOrderByTimestamp(WaterTank waterTank, Date startDate, Date endDat);


    @Query(value="select * from water_tank_data e where e.timestamp between :di and :df", nativeQuery = true)
    List<WaterTankData> findByDateRange(@Param("di") LocalDate startDate,
                                        @Param("df") LocalDate finalDate);

    @Query(value="select * from water_tank_data e where e.water_tank_id = :tid", nativeQuery = true)
    List<WaterTankData> findByWaterTank(@Param("tid") Long tankId);
}
