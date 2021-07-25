package com.flight.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Airline {

	@Id
	private int airId;
	private String airlineName;
	private String model;
	private String address;
	private long contactNumber;
	private String url;
	
	@OneToMany(mappedBy = "airline")
	private Set<Flight> flights = new HashSet<Flight>();
	
	public Airline() {
		
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAirId() {
		return airId;
	}

	public void setAirId(int airId) {
		this.airId = airId;
	}
	
	
}
