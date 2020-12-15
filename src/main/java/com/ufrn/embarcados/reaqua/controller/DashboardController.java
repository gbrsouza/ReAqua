package com.ufrn.embarcados.reaqua.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.service.TowerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DashboardController {

	private final TowerService towerService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model){
		List<Tower> towers = towerService.listAll();
		//TODO substituir pela torre do usuário logado 
		Tower selectedTower = towerService.findById(1L).get();
		//TODO substituir por dados consultados do banco
		Map<String, Integer> waterConsumption = getWaterConsumptionMap();
		
		model.addAttribute("selectedTower", selectedTower);
		model.addAttribute("towers", towers);
		model.addAttribute("dates", waterConsumption.keySet());
		model.addAttribute("consumption", Arrays.asList(waterConsumption.values()));
		return "dashboard";
	}
	
	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public String towerBoard(Model model, @PathVariable String id){
		List<Tower> towers = towerService.listAll();
		Tower selectedTower = towerService.findById(Long.valueOf(id)).get();
		//TODO substituir por dados consultados do banco
		Map<String, Integer> waterConsumption = getWaterConsumptionMap();
		
		model.addAttribute("selectedTower", selectedTower);
		model.addAttribute("towers", towers);
		model.addAttribute("dates", waterConsumption.keySet());
		model.addAttribute("consumption", Arrays.asList(waterConsumption.values()));
		return "dashboard";
	}
	
	private Map<String, Integer> getWaterConsumptionMap() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -6);
		SimpleDateFormat format = new SimpleDateFormat("EEE");
		for (int i=0; i<7; i++) {
			map.put("\'" + format.format(date.getTime()) + "\'", (int) (Math.random()*100));
			date.add(Calendar.DATE, 1);
		}
		return map;
	}

}
