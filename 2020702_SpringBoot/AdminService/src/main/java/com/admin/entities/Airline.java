package com.admin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int airlineId;
	private String airlineName;
	private String airlineModel;
	private String airlineLogo;
	private long contactNumber;
	
	
	public Airline(int airlineId, String airlineName, String airlineModel, String airlineLogo,long contactNumber) {
		this.airlineName = airlineName;
		this.airlineId = airlineId;
		this.airlineModel = airlineModel;
		this.airlineLogo = airlineLogo;
		this.contactNumber = contactNumber;
	}


	public Airline() {
	}


	public String getAirlineName() {
		return airlineName;
	}


	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}


	public String getAirlineModel() {
		return airlineModel;
	}


	public void setAirlineModel(String airlineModel) {
		this.airlineModel = airlineModel;
	}


	public String getAirlineLogo() {
		return airlineLogo;
	}


	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}
	
	public long getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public int getAirlineId() {
		return airlineId;
	}


	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	@Override
	public String toString() {
		return "Airline [airlineName=" + airlineName + ", airlineModel=" + airlineModel + ", airlineLogo=" + airlineLogo
				+  ", contactNumber=" + contactNumber + "]";
	}
	
}
