package com.user.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passengerId;
	
	private String passengerName;
	private String preference;
	private String passengerEmail;
	private long mobileNumber;
	
	@ManyToOne
	@JoinColumn(name = "flightId" )
	private Flight flight;
	
	public Passenger() {}
	
	public Passenger(int passengerId, String passengerName, String preference, String passengerEmail,
			long mobileNumber) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.preference = preference;
		this.passengerEmail = passengerEmail;
		this.mobileNumber = mobileNumber;
	}



	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public String getPassengerEmail() {
		return passengerEmail;
	}
	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", preference="
				+ preference + ", passengerEmail=" + passengerEmail + ", mobileNumber=" + mobileNumber + "]";
	}
	
	
	
}
