package com.ufrn.embarcados.reaqua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.service.TowerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WaterTankController {
	
	private final TowerService towerService;
	
	@RequestMapping(value = "/waterTanks", method = RequestMethod.GET)
	public String waterTanksBoard(Model model){
		List<Tower> towers = towerService.listAll();
		model.addAttribute("towers", towers);
		return "waterTanks";
	}

}
