package com.spring.service;

import java.util.List;

import com.spring.entity.Reading;
import com.spring.entity.Readings;
import com.spring.entity.Vehicle;

public interface CarTrackerService {

	public Vehicle createVehicle(Vehicle vehicle);
	public Readings createReading(Reading readings);
	public List<Vehicle> returnVehicles();
	public List<Readings> returnReadingsOfVehicle(String vin);
	
}
