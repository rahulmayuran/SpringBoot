package com.flight.app.service;

import com.flight.app.Model.Flight;
import com.flight.app.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements FlightServiceInterface{

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<Flight> getAllflights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFLightByFlightId(int id) {
        return flightRepository.getById(id);
    }

    @Override
    public void cancelFlight(int fId) {
        System.out.println("Flight with id -> "+ fId+"is deleted");
        flightRepository.deleteById(fId);
    }
}
