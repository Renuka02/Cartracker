package com.spring.dao;

import java.util.List;

import com.spring.entity.Reading;
import com.spring.entity.Readings;
import com.spring.entity.Vehicle;

public interface CarTrackerDAO {

	public Vehicle createVehicle(Vehicle vehicle);
	public Readings createReadings(Reading reading);
	public String createAlert(Readings readings);
	public List<Vehicle> returnVehicles();
	public List<Readings> returnReadingsOfVehicle(String vin);
}
