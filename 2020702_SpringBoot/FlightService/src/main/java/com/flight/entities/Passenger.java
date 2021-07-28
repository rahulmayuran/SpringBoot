package com.flight.entities;

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
@JsonInclude(value = Include.NON_DEFAULT)
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int passengerId;
	
	private String passengerName;
	private String passengerPreferences;
	private int passengerAge;
	private String passengerEmail;
	private long passengerContact;
	
	@ManyToOne
	@JoinColumn(name = "ticketId" , referencedColumnName = "ticketId")
	@JsonIgnoreProperties("passenger")
	private Booking booking;
	


	public Passenger()
	{
			
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

	public String getPassengerPreferences() {
		return passengerPreferences;
	}

	public void setPassengerPreferences(String passengerPreferences) {
		this.passengerPreferences = passengerPreferences;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengerEmail() {
		return passengerEmail;
	}

	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}

	public long getPassengerContact() {
		return passengerContact;
	}

	public void setPassengerContact(long passengerContact) {
		this.passengerContact = passengerContact;
	}
	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
