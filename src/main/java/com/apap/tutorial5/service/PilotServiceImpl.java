package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//PilotServiceImpl

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	public PilotModel getPilotDetailById(long id) {
		return pilotDb.findById(id);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public PilotModel deletePilot(long id) {
		PilotModel deleted = pilotDb.findById(id);
		pilotDb.delete(pilotDb.findById(id));
		return deleted;
	}

	@Override
	public PilotModel updatePilot(long id, String name, String flyHour) {
		pilotDb.findById(id).setName(name);
		pilotDb.findById(id).setFlyHour(flyHour);
		PilotModel updated = pilotDb.findById(id);
		return updated;
	}
	
}
