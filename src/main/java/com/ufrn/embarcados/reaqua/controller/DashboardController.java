package com.ufrn.embarcados.reaqua.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufrn.embarcados.reaqua.model.ApplicationUser;
import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.model.WaterTank;
import com.ufrn.embarcados.reaqua.model.WaterTankData;
import com.ufrn.embarcados.reaqua.service.TowerService;
import com.ufrn.embarcados.reaqua.service.WaterTankDataService;
import com.ufrn.embarcados.reaqua.util.Role;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DashboardController {

	private final TowerService towerService;

	private final WaterTankDataService waterTankDataService;
	
	/** Number of days to query the consumption records */
	private final Integer lookbackPeriod = 6;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model){
		ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean admin = user.getRole().equals(Role.ADMIN);
		
		List<Tower> towers = towerService.listAll();
		Tower selectedTower = towerService.findById(user.getTower().getId()).get();

		model.addAttribute("admin", admin);
		model.addAttribute("username", user.getName());
		model.addAttribute("selectedTower", selectedTower);
		model.addAttribute("towers", towers);
		model.addAttribute("dates", getWaterConsumptionDateList());
		model.addAttribute("consumption", getWaterConsumption(selectedTower));
		return "dashboard";
	}

	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public String towerBoard(Model model, @PathVariable String id){
		ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean admin = user.getRole().equals(Role.ADMIN);
		model.addAttribute("admin", admin);
		
		if (admin) {
			List<Tower> towers = towerService.listAll();
			Tower selectedTower = towerService.findById(Long.valueOf(id)).get();
	
			model.addAttribute("username", user.getName());
			model.addAttribute("selectedTower", selectedTower);
			model.addAttribute("towers", towers);
			model.addAttribute("dates", getWaterConsumptionDateList());
			model.addAttribute("consumption", getWaterConsumption(selectedTower));
			return "dashboard";
		} else
			return "redirect:/dashboard";
	}

	private List<String> getWaterConsumptionDateList() {
		List<String> dates = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("EEE");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -lookbackPeriod);

		for (int i=0; i<=lookbackPeriod; i++) {
			dates.add("\'" + format.format(date.getTime()) + "\'");
			date.add(Calendar.DATE, 1);
		}

		return dates;
	}

	private List<List<Integer>> getWaterConsumption(Tower tower) {
		List<List<Integer>> lists = new ArrayList<List<Integer>>();

		for (WaterTank tank: tower.getWaterTanks()) {
			List<Integer> consumption = new ArrayList<Integer>();
			Calendar startDate = Calendar.getInstance();
			Calendar endDate = Calendar.getInstance();
			startDate.add(Calendar.DATE, -lookbackPeriod);
			startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE), 0, 0, 0);
			endDate.add(Calendar.DATE, -lookbackPeriod);
			endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DATE), 23, 59, 59);

			for (int i=0; i<=lookbackPeriod; i++) {
				List<WaterTankData> data = waterTankDataService.findByWaterTankAndDate(tank, startDate.getTime(), endDate.getTime());

				Integer waterUse = 0;
				Integer waterFill = 0;
				Integer previousCapacity = null;
				for (WaterTankData d: data) {
					if (previousCapacity != null) {
						if (d.getLevel() > previousCapacity) {
							waterFill += (d.getLevel() - previousCapacity);
						} else if (d.getLevel() < previousCapacity) {
							waterUse += (previousCapacity - d.getLevel());
						}
					}

					previousCapacity = d.getLevel();
					System.out.println(d.toString());
				}

				startDate.add(Calendar.DATE, 1);
				endDate.add(Calendar.DATE, 1);
				consumption.add(waterUse);
			}

			lists.add(consumption);
		}

		return lists;
	}

}
