package com.admin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entities.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Integer>{

}
