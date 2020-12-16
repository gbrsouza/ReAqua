package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.repository.TowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TowerService {

    private final TowerRepository towerRepository;

    public List<Tower> listAll(){
        return towerRepository.findAll();
    }
    
    public Optional<Tower> findById(Long id) {
    	return towerRepository.findById(id);
    }

    public void save(Tower tower) {
        towerRepository.save(tower);
    }

    //@TODO Este método está com problemas
    public Tower getByWaterTank(WaterTank waterTank) {
        Tower tower = listAll().stream().filter(t -> {
           return t.getWaterTanks().contains(waterTank);
        }).findFirst().get();
        return tower;
    }

    public Tower getById(Long towerId) {
        return towerRepository.findById(towerId).get();
    }
}
