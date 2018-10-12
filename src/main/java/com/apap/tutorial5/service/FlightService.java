package com.apap.tutorial5.service;

import java.sql.Date;
import com.apap.tutorial5.model.FlightModel;

//FlightService

public interface FlightService {
	void addFlight(FlightModel flight);
	FlightModel deleteFlight(long id);
	FlightModel updateFlight(long id, String flightNumber, String origin, String destination, Date time);
	FlightModel getFlightDetailById(long id);
}
