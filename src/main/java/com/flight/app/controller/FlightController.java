package com.flight.app.controller;

import com.flight.app.Model.Flight;
import com.flight.app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/home")
    public String greet(){
        return "Welcome to Flight App";
    }

    @GetMapping("/flightList")
    public List<String> flightList(){
        return Arrays.asList("AirIndia","Go-Air","Indigo");
    }

    @GetMapping("/flightList")
    public Flight getFlightById(int id){
        return flightService.getFLightByFlightId(id);
    }
}
