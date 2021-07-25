package com.admin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
//This annotation is used to ignore null values in JSON object
@JsonInclude(value = Include.NON_DEFAULT)
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int flightId;
	private int noOfSeats;
	private int price;
	private String journey;
	private String destination;
	
	@ManyToOne
	@JoinColumn(name = "airlineId" , referencedColumnName = "airlineId")
	@JsonIgnoreProperties("flight")
	private Airline airline;


	public Flight() {}

	public Flight(int flightId, int noOfSeats, int price, String journey, String destination) {
		super();
		this.flightId = flightId;
		this.noOfSeats = noOfSeats;
		this.price = price;
		this.journey = journey;
		this.destination = destination;
	}

	public int getFlightId() {return flightId;}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getJourney() {
		return journey;
	}

	public void setJourney(String journey) {
		this.journey = journey;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", noOfSeats=" + noOfSeats + ", price=" + price + ", journey=" + journey
				+ ", destination=" + destination + "]";
	}
	
	
}
