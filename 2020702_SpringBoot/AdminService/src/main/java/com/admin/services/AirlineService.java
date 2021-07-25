package com.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entities.Airline;
import com.admin.repositories.AirlineRepository;

@Service
public class AirlineService {

	@Autowired
	AirlineRepository airlineRepository;
	
	public Airline saveAirline(Airline airline) {
		return airlineRepository.save(airline);
	}
	
	public List<Airline> getAllAirlines(){
		return airlineRepository.findAll();
	}
	
	public String deleteAirline(int id) {
		airlineRepository.deleteById(id);
		return "Flight Deleted";
	}
}
