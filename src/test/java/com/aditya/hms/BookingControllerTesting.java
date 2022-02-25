package com.aditya.hms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aditya.hms.controller.BookingController;
import com.aditya.hms.model.Booking;
import com.aditya.hms.service.BookingService;

@SpringBootTest(classes= {BookingControllerTesting.class})
public class BookingControllerTesting {
	
	@Mock
	private BookingService bookingService;
	
	@InjectMocks
	private BookingController bookingController=new BookingController();
	
	private List<Booking>bookings=new ArrayList<>();
	@Test
	public void saveBookingTest() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Booking booking=new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0);

		when(bookingService.saveBooking(booking)).thenReturn(booking);
		ResponseEntity<?>res=bookingController.saveBooking(booking);
		assertEquals(res.getStatusCode(),HttpStatus.CREATED);
		
	}
	@Test
	public void cancelBookingTest() throws ParseException {
		long id=1;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		Optional<Booking> booking=Optional.of(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		String s="Booking cancelled with bookingid "+booking.get().getId();
		when(bookingService.cancelbooking(id)).thenReturn(s);
		ResponseEntity<?>res=bookingController.cancelBooking(id);
		assertEquals(res.getStatusCode(),HttpStatus.OK);
		assertEquals(res.getBody(),s);
	}

}
