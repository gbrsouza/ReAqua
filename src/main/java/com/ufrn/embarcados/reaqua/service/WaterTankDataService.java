package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.model.WaterTankData;
import com.ufrn.embarcados.reaqua.repository.WaterTankDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WaterTankDataService {

	private final WaterTankDataRepository waterTankDataRepository;

	public List<WaterTankData> listAll(){
		return waterTankDataRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
	}
	
	public List<WaterTankData> findByWaterTankAndDate(WaterTank waterTank, Date startDate, Date endDate){
		return waterTankDataRepository.findByWaterTankAndDate(waterTank, startDate, endDate);
	}

}
