package com.flight.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{

	//Searches Passenger By name. 
    public List<Passenger> findByPassengerName(String pname);

}