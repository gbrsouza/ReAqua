package com.ufrn.embarcados.reaqua.controller;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.model.WaterTankData;
import com.ufrn.embarcados.reaqua.service.TowerService;
import com.ufrn.embarcados.reaqua.service.WaterTankDataService;
import com.ufrn.embarcados.reaqua.service.WaterTankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HardwareApi {

    @Autowired
    private WaterTankDataService waterTankDataService;

    @Autowired
    private WaterTankService waterTankService;

    @Autowired
    private TowerService towerService;

    @RequestMapping(value = "/{tower-id}/water-tank", method = RequestMethod.POST)
    public String registerWaterTank(HttpServletRequest request,
                                    @PathVariable("tower-id") Long towerId,
                                    @RequestBody WaterTank waterTank)
    {
        waterTankService.saveTankInTower(waterTank, towerId);
        return "Water Tank Registered";
    }

    @RequestMapping(value = "/{tank-id}/water-level", method = RequestMethod.POST)
    public String registerLevel(HttpServletRequest request,
                                 @PathVariable("tank-id") Long tankId,
                                 @RequestBody WaterTankData waterTankData){
        try {
            waterTankData.setWaterTank(
                    waterTankService.getById(tankId));
            waterTankDataService.save(waterTankData);
        }
        catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Nivel recebido";
    }

    @RequestMapping(value = "/water-levels", method = RequestMethod.GET)
    public ResponseEntity<List<WaterTankData>> getNiveis(HttpServletRequest request)
    {
        List<WaterTankData> niveis;
        ResponseEntity<List<WaterTankData>> re;

        try {
            niveis = waterTankDataService.getAllData();
            re = new ResponseEntity<> (niveis, HttpStatus.OK);
        } catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }

        return re;
    }

    @RequestMapping(value = "/water-levels/{dataInicial}/{dataFinal}", method = RequestMethod.GET)
    public ResponseEntity<List<WaterTankData>> showLevelsByDay(
            HttpServletRequest request,
            @PathVariable("dataInicial") String dataInicial,
            @PathVariable("dataFinal") String dataFinal)
    {
        List<WaterTankData> niveis;
        ResponseEntity<List<WaterTankData>> re;

        try {
            niveis = waterTankDataService.getLevelsByDays(
                    LocalDate.parse(dataInicial) , LocalDate.parse(dataFinal));
            re = new ResponseEntity<>(niveis, HttpStatus.OK);
        }
        catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }

        return re;
    }
}
