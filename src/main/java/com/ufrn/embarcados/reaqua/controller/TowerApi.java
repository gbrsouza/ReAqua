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
public class TowerApi {

    @Autowired
    private WaterTankDataService waterTankDataService;

    @Autowired
    private WaterTankService waterTankService;

    @Autowired
    private TowerService towerService;

    @RequestMapping(value = "/tower", method = RequestMethod.POST)
    public String registerWaterTank(HttpServletRequest request,
                                    @RequestBody Tower tower)
    {
        towerService.save(tower);
        return "Tower Registered";
    }

    @RequestMapping(value = "/towers", method = RequestMethod.GET)
    public ResponseEntity<List<Tower>> getTowers(HttpServletRequest request)
    {
        List<Tower> towers;
        ResponseEntity<List<Tower>> re;

        try {
            towers = towerService.listAll();
            re = new ResponseEntity<> (towers, HttpStatus.OK);
        } catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }

        return re;
    }

}
