package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//PilotController

@Controller
public class PilotController {

	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	public String deletePilot(@PathVariable(value = "id") long id, Model model) {
		pilotService.deletePilot(id);
		return "delete";
	}
	
	@RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "id") long id, Model model) {
		PilotModel pilot = pilotService.getPilotDetailById(id);
		model.addAttribute("pilot", pilot);
		return "update-pilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("flyHour") String flyHour, Model model) {
		PilotModel updatedPilot = pilotService.updatePilot(id, name, flyHour);
		model.addAttribute("pilot", updatedPilot);
		return "updated-pilot";
	}
	
}
