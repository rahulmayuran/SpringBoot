package com.flight.controllers;

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

import com.flight.entities.Passenger;
import com.flight.services.PassengerService;


@RestController
@RequestMapping("/api/v1.0/flight")
@CrossOrigin(origins = "http://localhost:4200")
public class PassengerController 
{
	
	@Autowired
	PassengerService passengerService;

	@GetMapping("/passengers")
	public List<Passenger> passengers() {
		System.out.println("Get all passengers from Spring boot");
		List<Passenger> passengerList = passengerService.getPassengers();
		return passengerList;
	}
	
	@PostMapping("/passenger/register")
	public Passenger saveDiscountToDB(@RequestBody Passenger saveMePls)
	{
		System.out.println("Saved Passenger from Spring boot - "+saveMePls);
		return passengerService.savePassenger(saveMePls);
	}
	
	@DeleteMapping("/passenger/delete/{id}")
	public String deleteFlightById(@PathVariable int id){
		try {
			System.out.println("Deleting Passenger.." + id);
			passengerService.deletePassenger(id);
			return "Deleted Passenger(Spring Boot) with id "+ id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Some Error Occured while deleting";
		}
	}
}
