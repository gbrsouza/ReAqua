package com.ufrn.embarcados.reaqua.controller;

import java.util.List;

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
		model.addAttribute("selectedTower", selectedTower);
		model.addAttribute("towers", towers);
		return "dashboard";
	}
	
	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public String towerBoard(Model model, @PathVariable String id){
		List<Tower> towers = towerService.listAll();
		Tower selectedTower = towerService.findById(Long.valueOf(id)).get();
		model.addAttribute("selectedTower", selectedTower);
		model.addAttribute("towers", towers);
		return "dashboard";
	}

}
