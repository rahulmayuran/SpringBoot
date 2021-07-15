package com.flight.app.controller;

import com.flight.app.Model.Flight;
import com.flight.app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public String greet(){
        return "Welcome to Flight App";
    }

    @GetMapping("/demo")
    public Flight getUsersFromServer(){
        String url = "http://localhost:3000";
        Flight flight = new Flight();
        Flight result = restTemplate.postForObject(url, flight, Flight.class);
        System.out.println(result);
        return flight;
    }

    @GetMapping("/arrayList")
    public List<String> flightList(){
        return Arrays.asList("AirIndia","Go-Air","Indigo");
    }

    @GetMapping("/flight/{id}")
    public Flight getFlightById(@PathVariable  int id)
    {
        return flightService.getFLightByFlightId(id);
    }
}
