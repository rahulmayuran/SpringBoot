package com.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entities.Booking;
import com.flight.services.BookingService;

@RestController
@RequestMapping("/api/v1.0/flight")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController 
{
	
	@Autowired
	BookingService bookingService;

	@GetMapping("/bookings")
	public List<Booking> getAllBookingInfo() {
		System.out.println("Get all booking history from User");
		List<Booking> bookingList = bookingService.getAllBookings();
		return bookingList;
	}
	
	@PostMapping("/booking/register")
	@CrossOrigin(origins = "http://localhost:4200")
	public Booking saveTicket(@RequestBody Booking saveBooking)
	{
		System.out.println("Saved Booking from Spring boot - "+saveBooking);
			return bookingService.saveBooking(saveBooking);
	}
	
	@DeleteMapping("/booking/delete/{id}")
	public String deleteTicketById(@PathVariable int id){
		try {
			System.out.println("Deleting Ticket.." + id);
			bookingService.deleteTicket(id);
			return "Deleted Ticket(Spring Boot) with id "+ id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Some Error Occured while deleting";
		}
	}
}
