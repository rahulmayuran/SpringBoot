package com.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{

	//Searches Flight By name. 
//    public List<Flight> findByName(String fname);

	//public Flight getBookingHistoryByEmail(String emailId);

//    @Query("SELECT f.price FROM Flight f WHERE f.price > ?1 and f.price < ?2")
//    public List<Flight> findFlightsByPrice(double price);

}
