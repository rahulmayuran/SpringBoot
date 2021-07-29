package com.flight.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Passenger {

	private String passengerName;
	private String passengerPreferences;
	private int passengerAge;
	private String passengerEmail;
	private long passengerContact;


	public Passenger()
	{
			
	}
	
	
	public Passenger(String passengerName, String passengerPreferences, int passengerAge, String passengerEmail,
			long passengerContact) {
		super();
		this.passengerName = passengerName;
		this.passengerPreferences = passengerPreferences;
		this.passengerAge = passengerAge;
		this.passengerEmail = passengerEmail;
		this.passengerContact = passengerContact;
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

	@Override
	public String toString() {
		return "Passenger [passengerName=" + passengerName + ", passengerPreferences=" + passengerPreferences
				+ ", passengerAge=" + passengerAge + ", passengerEmail=" + passengerEmail + ", passengerContact="
				+ passengerContact + "]";
	}

	
}
