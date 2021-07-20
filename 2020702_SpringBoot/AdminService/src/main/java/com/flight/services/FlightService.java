package com.flight.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entities.Flight;
import com.flight.repositories.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository flightRepository;
	
	
	//Provided Default By JPA
	public List<Flight> findAllFlights(){
		return flightRepository.findAll();
	}
	
	//JPA 
	public Flight findFlightById(int id)
	{
		System.out.println("in service id: "+id);
		Optional<Flight> optional = flightRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	//JPA
	public Flight saveFlight(Flight flight){
		return flightRepository.save(flight);
	}

	//Customized in FlightRepository to filter with names
//    public List<Flight> findFlightsByName(String fname) {
//        return flightRepository.findByName(fname);
//    }
//    
//    public Flight bookFlightById(Flight flight, int flightId) {
//    	return flightRepository.bookFlightById(flight,flightId);
//    }
//
//	public Flight getBookingHistory(String emailId) {
//		return flightRepository.getBookingHistoryByEmail(emailId);
//	}

	//Customized in FlightRepository to filter with prices
//    public List<Flight> findFlightBasedOnPrice(double price) {
//        return repository.findFlightsByPrice(price);
//    }

}