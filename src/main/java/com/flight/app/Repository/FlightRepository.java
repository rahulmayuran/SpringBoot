package com.flight.app.Repository;

import com.flight.app.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class FlightRepository implements JpaRepository<Flight, Integer> {
}
