package com.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.Flight;
import com.flight.services.FlightService;

@RestController
@RequestMapping("/api/v1.0/flight")
@CrossOrigin(origins = "http://localhost:4200/*")
public class FlightController {

	@Autowired
	FlightService flightservice;
	
	//Cacheable can be used if there are multiple hits to the 
		//DB as its inevitable and you have to improve speed of hitting req.
	@GetMapping("/search")
//	@Cacheable(value="flights")
	public List<Flight> AllFlights(){
		System.out.println("finding all flights from db");
		return flightservice.findAllFlights();
	}

//	@GetMapping("/search/{id}")
//	@Cacheable(key="#id", value="flights", condition="#id==1")
//	public Flight findFlightById(@PathVariable int id){
//		System.out.println("finding flight ("+id+") from db");
//		return flightservice.findFlightById(id);
//	}
//
	//response to be sent from Postman or from Angular.
	@PostMapping("/airline/register")
	public Flight persistFlight(@RequestBody Flight flight){
		return flightservice.saveFlight(flight);
	}
//	
//	//Locked flight for booking, a response to be sent from Postman or from Angular.
//	@PostMapping("/booking/{flightid}")
//	public Flight bookFlight(@RequestBody Flight flight, @PathVariable int flightid) {
//		return flightservice.bookFlightById(flight,flightid);
//		
//	}
//	
//	//List the booked flights based on passenger's email
//	@GetMapping("/booking/history/{emailId}")
//	public Flight BookingHistory(@PathVariable String emailId) {
//		return flightservice.getBookingHistory(emailId);
//	}
	

	//response to be sent from Postman or from Angular.
	@DeleteMapping("/booking/cancel/{pnr}")
	@CacheEvict(key ="#id", value="flights")
	public String cancelFlight(@PathVariable String pnr) {
		return "Flight is cancelled successfully";
		
	}
}
