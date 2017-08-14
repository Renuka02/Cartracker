package com.tracker.cartracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Reading;
import com.spring.entity.Readings;
import com.spring.entity.Vehicle;
import com.spring.service.CarTrackerService;

@RestController
@RequestMapping("/cartracker")
public class CarTrackerController {
	
	@Autowired
	private CarTrackerService cartrackerservice;
	

	@RequestMapping(value="/Sample" , method = RequestMethod.GET)
	public void SampleRestApi(){
		System.out.println("Sample run Api");
	}
	
	// apis to get data from mocker
	@CrossOrigin
	@RequestMapping(value="/vehicles" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void putVehicle(@RequestBody Vehicle vehicle[]){
		//System.out.println(vehicle[0].getMaxFuelVolume());
		for (Vehicle vehicle2 : vehicle) {
			cartrackerservice.createVehicle(vehicle2);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/readings" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postReadings(@RequestBody Reading reading){
		//System.out.println(reading.getLatitude());
		Readings new_reading = cartrackerservice.createReading(reading);
		//System.out.println(new_reading);
	}
	
	
	//apis to send data to ui
	//send a list of vehicles with their details
	@CrossOrigin
	@RequestMapping(value="/readvehicles" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vehicle> getVehicles(){
		List<Vehicle> result = cartrackerservice.returnVehicles();
		return result;
	}
	
	@CrossOrigin
	@RequestMapping(value="/getreading/{vin}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Readings> getReadings(@PathVariable(value = "vin") String vin){
		List<Readings> result = cartrackerservice.returnReadingsOfVehicle(vin);
		return result;
	}
	
	
}
