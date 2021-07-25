package com.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entities.Passenger;
import com.user.repositories.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	PassengerRepository passengerRepository;
	
	public List<Passenger> getPassengers(){
		return passengerRepository.findAll();
	}
	
	public Passenger savePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
}
