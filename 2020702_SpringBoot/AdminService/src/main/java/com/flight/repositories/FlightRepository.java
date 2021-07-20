package com.flight.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

	//Searches Flight By name. 
//    public List<Flight> findByName(String fname);
//
//	public Flight bookFlightById(Flight flight, int flightId);
//
//	public Flight getBookingHistoryByEmail(String emailId);

//    @Query("SELECT f.price FROM Flight f WHERE f.price > ?1 and f.price < ?2")
//    public List<Flight> findFlightsByPrice(double price);

}
