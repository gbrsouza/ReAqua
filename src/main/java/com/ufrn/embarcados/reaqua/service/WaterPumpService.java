package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.WaterPump;
import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.repository.WaterPumpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WaterPumpService {
    private final WaterPumpRepository waterPumpRepository;

    private WaterTankService waterTankService;

    public List<WaterPump> listAll(){
        return waterPumpRepository.findAll();
    }

    public void save(WaterPump waterPump) {
        waterPumpRepository.save(waterPump);
    }

}
