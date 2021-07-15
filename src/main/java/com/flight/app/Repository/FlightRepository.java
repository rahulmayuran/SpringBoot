package com.flight.app.Repository;

import com.flight.app.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class FlightRepository implements JpaRepository<Flight, Integer> {

}
