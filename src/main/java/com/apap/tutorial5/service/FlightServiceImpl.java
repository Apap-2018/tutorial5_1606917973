package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//FlightServiceImpl

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public FlightModel deleteFlight(long id) {
		FlightModel deleted = flightDb.findById(id);
		flightDb.delete(flightDb.findById(id));
		return deleted;
	}

	@Override
	public FlightModel updateFlight(long id, String flightNumber, String origin, String destination, Date time) {
		flightDb.findById(id).setFlightNumber(flightNumber);
		flightDb.findById(id).setOrigin(origin);
		flightDb.findById(id).setDestination(destination);
		flightDb.findById(id).setTime(time);
		FlightModel updated = flightDb.findById(id);
		return updated;
	}

	@Override
	public FlightModel getFlightDetailById(long id) {
		return flightDb.findById(id);
	}
	
	
}
