package com.ufrn.embarcados.reaqua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.model.WaterTankData;
import com.ufrn.embarcados.reaqua.service.TowerService;
import com.ufrn.embarcados.reaqua.service.WaterTankDataService;
import com.ufrn.embarcados.reaqua.util.Role;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WaterTankController {
	
	private final TowerService towerService;
	
	private final WaterTankDataService waterTankDataService;
	
	@RequestMapping(value = "/waterTanks", method = RequestMethod.GET)
	public String waterTanksBoard(Model model){
		ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean admin = user.getRole().equals(Role.ADMIN);
		model.addAttribute("admin", admin);
		
		if (admin) {
			List<Tower> towers = towerService.listAll();
			model.addAttribute("towers", towers);
			return "waterTanks";
		} else
			return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "/waterTanksData", method = RequestMethod.GET)
	public String waterTanksDataBoard(Model model){
		List<WaterTankData> data = waterTankDataService.listAll();
		model.addAttribute("data", data);
		return "waterTanksData";
	}

}
