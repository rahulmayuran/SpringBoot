package com.flight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{


}
