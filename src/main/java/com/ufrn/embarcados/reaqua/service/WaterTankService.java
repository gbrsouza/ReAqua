package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.repository.TowerRepository;
import com.ufrn.embarcados.reaqua.repository.WaterTankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WaterTankService {
    @Autowired
    private WaterTankRepository waterTankRepository;

    private TowerService towerService;

    public void saveTankInTower(WaterTank waterTank, Long towerId) {
        //waterTankRepository.save(waterTank);
        Tower tower = towerService.getById(towerId);
        if (tower != null){
            waterTankRepository.save(waterTank);
            tower.getWaterTanks().add(waterTank);
            towerService.save(tower);
        }
    }

    public WaterTank getById(Long tankId) {
        return waterTankRepository.findById(tankId).get();
    }
}
