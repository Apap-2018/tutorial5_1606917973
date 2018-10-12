package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.service.PilotService;
import com.apap.tutorial5.service.FlightService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//FlightController

@Controller
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		ArrayList<FlightModel> flightList = new ArrayList<FlightModel>();
		flightList.add(flight);
		pilot.setPilotFlight(flightList);
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"addRow"})
	public String addRow(@PathVariable(value="licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		FlightModel newflight = new FlightModel();
		pilot.getPilotFlight().add(newflight);
		
		model.addAttribute("pilot", pilot);
	    return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"submit"})
	private String addFlightSubmit(@PathVariable(value="licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot) {
		PilotModel pilotDb = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(pilotDb);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
	private String viewFlight(@RequestParam("id") long id, Model model) {
		FlightModel flight = flightService.getFlightDetailById(id);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", flight.getPilot());
		return "view-flight";
	}

	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	public String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "delete";
	}
	
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	public String deleteFlight(@PathVariable(value = "id") long id, Model model) {
		flightService.deleteFlight(id);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "id") long id, Model model) {
		FlightModel flight = flightService.getFlightDetailById(id);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", flight.getPilot());
		return "update-flight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@RequestParam("id") long id, @RequestParam("flightNumber") String flightNumber, @RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestParam("time") Date time, Model model) {
		FlightModel updatedFlight = flightService.updateFlight(id, flightNumber, origin, destination, time);
		model.addAttribute("flight", updatedFlight);
		model.addAttribute("pilot", updatedFlight.getPilot());
		return "updated-flight";
	}
	
	
}
