package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dao.CarTrackerDAO;
import com.spring.dataclasses.HighAlerts;
import com.spring.dataclasses.LatitudeLongitude;
import com.spring.entity.Reading;
import com.spring.entity.Readings;
import com.spring.entity.Vehicle;

public class CarTrackerServiceImpl implements CarTrackerService{

	@Autowired
	private CarTrackerDAO cartrackerDAO;
	
	public Vehicle createVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return cartrackerDAO.createVehicle(vehicle);
	}

	public Readings createReading(Reading readings) {
		// TODO Auto-generated method stub
		return cartrackerDAO.createReadings(readings);
	}

	public List<Vehicle> returnVehicles() {
		// TODO Auto-generated method stub
		return cartrackerDAO.returnVehicles();
	}

	public List<Readings> returnReadingsOfVehicle(String vin ) {
		// TODO Auto-generated method stub
		return cartrackerDAO.returnReadingsOfVehicle(vin);
	}

	public List<Readings> returnReadingsOfVehicle(String vin , int hours , int minutes) {
		// TODO Auto-generated method stub
		return cartrackerDAO.returnReadingsOfVehicle(vin , hours , minutes);
	}

	public List<HighAlerts> returnReadingsofHighAlerts() {
		// TODO Auto-generated method stub
		return cartrackerDAO.returnReadingsofHighAlerts();
	}

	public Vehicle returnVehicle(String vin) {
		// TODO Auto-generated method stub
		return cartrackerDAO.returnVehicle(vin);
	}

	public List<LatitudeLongitude> returnLatLng(String vin) {
		// TODO Auto-generated method stub
		return cartrackerDAO.returnLatLng(vin);
	}

	

	
}
