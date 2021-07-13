package com.flight.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FlightController {

    @GetMapping("/home")
    public String greet(){
        return "Welcome to Flight App";
    }

    @GetMapping("/flightList")
    public List<String> flightList(){
        return Arrays.asList("AirIndia","Go-Air","Indigo");
    }
}
