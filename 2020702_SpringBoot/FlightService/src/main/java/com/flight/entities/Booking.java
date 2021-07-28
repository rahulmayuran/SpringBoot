package com.flight.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketId;
	private String PNR_number;
	
	@OneToMany(mappedBy = "booking")
	@JsonIgnoreProperties("booking")
	private List<Passenger> passenger;
	
	private Date startDate;
	private Date endDate;
	private Date bookingDate;
	private String journey;
	private String destination;
	private int totalprice;
	
	public Booking() {
		
	}

	public Booking(int ticketId, String pNR_number, List<Passenger> passenger, Date startDate, Date endDate,
			Date bookingDate, String journey, String destination, int totalprice) {
		super();
		this.ticketId = ticketId;
		this.PNR_number = pNR_number;
		this.passenger = passenger;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookingDate = bookingDate;
		this.journey = journey;
		this.destination = destination;
		this.totalprice = totalprice;
	}



	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getPNR_number() {
		return PNR_number;
	}

	public void setPNR_number(String pNR_number) {
		PNR_number = pNR_number;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

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

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
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

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	
	@Override
	public String toString() {
		return "Booking [ticketId=" + ticketId + ", PNR_number=" + PNR_number + ", passenger=" + passenger
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", bookingDate=" + bookingDate + ", journey="
				+ journey + ", destination=" + destination + ", totalprice=" + totalprice + "]";
	}

	
}
