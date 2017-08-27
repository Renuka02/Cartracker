package com.tracker.cartracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@ImportResource("project_app_config.xml")
public class CarTrackerApp {

	
	public static void main(String[] args){
		SpringApplication.run(CarTrackerApp.class, args);
	}
	
}
