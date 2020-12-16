package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.model.WaterTankData;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaterTankDataRepository extends JpaRepository<WaterTankData, Long> {
	
	@Query("select w from WaterTankData w where w.waterTank = :waterTank and w.timestamp < :endDate and w.timestamp > :startDate order by timestamp")
	List<WaterTankData> findByWaterTankAndDate(@Param("waterTank") WaterTank waterTank, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}