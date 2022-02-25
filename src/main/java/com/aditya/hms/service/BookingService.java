package com.aditya.hms.service;

import com.aditya.hms.model.Booking;

public interface BookingService {

	Booking saveBooking(Booking booking);
	
	String cancelbooking(long id);
	
}
