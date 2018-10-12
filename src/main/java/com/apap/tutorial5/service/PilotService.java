package com.apap.tutorial5.service;

import java.util.List;
import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;

//PilotService

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel getPilotDetailById(long id);
	void addPilot(PilotModel pilot);
	PilotModel deletePilot(long id);
	PilotModel updatePilot(long id, String name, String flyHour);
}
