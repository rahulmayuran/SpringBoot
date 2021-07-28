package com.admin.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entities.Airline;
import com.admin.services.AirlineService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1.0/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AirlineController {
	
	@Autowired
	AirlineService airlineService;
	
	@GetMapping("/airline")
	@ApiOperation(value = "Get all Airlines",
    	notes = "Already added as an admin",
    	response = List.class)
	public List<Airline> allAirlines() {
		List<Airline> airlines = airlineService.getAllAirlines();
		System.out.println("Get all airlines "+ Arrays.asList(airlines));
		return airlines;
	}
	
	@GetMapping("/airline/{id}")
	public Airline getAirlineById(@PathVariable int id) {
		Airline airline = new Airline();
		System.out.println("Get airline with id "+ id);
		return airline;
	}
	
	@PostMapping("/airline/register")
	public Airline saveAirline(@RequestBody Airline airline) {
		System.out.println("Save the airline ->"+ airline);
		return airlineService.saveAirline(airline);
	}
	
	@DeleteMapping("/airline/delete/{id}")
	public String deleteAirlineById(@PathVariable int id){
		try {
			System.out.println("Deleting Flight.." + id);
			airlineService.deleteAirline(id);
			return "Deleted Airline(Spring Boot) with id "+ id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Some Error Occured while deleting";
		}
	}
	
}
