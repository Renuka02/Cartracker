package com.spring.service;

import java.util.List;

import com.spring.dataclasses.HighAlerts;
import com.spring.dataclasses.LatitudeLongitude;
import com.spring.entity.Reading;
import com.spring.entity.Readings;
import com.spring.entity.Vehicle;

public interface CarTrackerService {

	public Vehicle createVehicle(Vehicle vehicle);
	public Readings createReading(Reading readings);
	public List<Vehicle> returnVehicles();
	public List<Readings> returnReadingsOfVehicle(String vin);
	public List<Readings> returnReadingsOfVehicle(String vin , int hours , int minutes);
	public List<HighAlerts> returnReadingsofHighAlerts();
	public Vehicle returnVehicle(String vin);
	public List<LatitudeLongitude> returnLatLng(String vin);
	
}
