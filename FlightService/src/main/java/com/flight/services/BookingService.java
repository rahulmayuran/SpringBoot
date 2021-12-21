package com.flight.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entities.Booking;
import com.flight.repositories.BookingRepository;


@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	public List<Booking> getAllBookings(){
		return bookingRepository.findAll();
	}
	
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public String deleteTicket(int id) {
		bookingRepository.deleteById(id);
		return "Deleted the ticket with id "+id;
		
	}
}
