package com.flight.app.service;

import com.flight.app.Model.Flight;

import java.util.List;

public interface FlightServiceInterface {

//    Flight operations

    public List<Flight> getAllflights();
    public Flight getFLightByFlightId(int id);
    public void cancelFlight(int id);

}
