package com.aditya.hms.service;

import java.util.List;

import com.aditya.hms.model.Booking;

public interface BookingService {

	Booking saveBooking(Booking booking);
	
	String cancelbooking(long id);
	
	List<Booking> getAllBookings(long id);
	
}
