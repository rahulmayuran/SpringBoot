package com.admin.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date endDate;
	
	
	@ManyToOne
	@JoinColumn(name = "airlineId" , referencedColumnName = "airlineId")
	@JsonIgnoreProperties("flights")
	private Airline airline;


	public Flight() {}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Flight(int flightId, int noOfSeats, int price, String journey, String destination, Date startDate,
			Date endDate, Airline airline) {
		super();
		this.flightId = flightId;
		this.noOfSeats = noOfSeats;
		this.price = price;
		this.journey = journey;
		this.destination = destination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.airline = airline;
		
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
