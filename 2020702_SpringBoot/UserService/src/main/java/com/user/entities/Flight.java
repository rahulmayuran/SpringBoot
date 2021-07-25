package com.user.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
//This annotation is used to ignore null values in JSON object
@JsonInclude(value = Include.NON_DEFAULT)
public class Flight {

	@Id
	private int flightId;

	
	private String flightName;

	private String flightModel;
	private double price;
	
	private String journey;
	private String destination;
	
//	@OneToMany(mappedBy = "flight")
//	private Set<Passenger> passenger = new HashSet<Passenger>();

	public Flight() {}
	
	public Flight(int flightId, String flightName, String flightModel,
			double price, String journey, String destination,Set<Passenger> passenger) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.flightModel = flightModel;
		this.price = price;
		this.journey = journey;
		this.destination = destination;
//		this.passenger = passenger;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightModel() {
		return flightModel;
	}

	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

//	public Set<Passenger> getPassenger() {
//		return passenger;
//	}
//
//	public void setPassenger(Set<Passenger> passenger) {
//		this.passenger = passenger;
//	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightName=" + flightName + ", flightModel=" + flightModel
				+ ", price=" + price + ", from=" + journey + ", to=" + destination + "]";
	}
	
	
}
