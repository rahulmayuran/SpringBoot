package com.admin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admin.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

	@Query("SELECT f FROM Flight f WHERE f.journey = ?1 and f.destination = ?2")
	public List<Flight> getFlightsByJourneyAndDestination(String journey,String destination);
	
	
//	public List<Flight> findByJourney(String journey);


}
