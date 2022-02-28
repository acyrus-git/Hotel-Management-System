package com.aditya.hms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.aditya.hms.exception.BusinessException;
import com.aditya.hms.model.Booking;
import com.aditya.hms.model.Hotel;
import com.aditya.hms.repository.BookingRepository;
import com.aditya.hms.repository.HotelRepository;
import com.aditya.hms.service.BookingService;
import com.aditya.hms.service.impl.BookingServiceImpl;

@SpringBootTest(classes= {BookingServiceTesting.class})
public class BookingServiceTesting {
	
	@Mock
	private BookingRepository bookingRepository;
	
	@Mock
	private HotelRepository hotelRepository;
	
	@InjectMocks
	private BookingService bookingService=new BookingServiceImpl();
	
	private List<Hotel>hotels=new ArrayList();
	private List<Booking>bookings=new ArrayList();

	
	@Test
	@Order(1)
	public void saveBookingTest_1() throws ParseException {
		
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		hotels.add(new Hotel(2,"Hotel Surya","Farrukhabad",1000,80,80));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		when(hotelRepository.findById((long) 1)).thenReturn(Optional.of(hotels.get(0)));
		when(hotelRepository.findAllByCity("Farrukhabad")).thenReturn(hotels);
		when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(1, 1,new java.sql.Date(sdf.parse("20/02/2022").getTime()) , new java.sql.Date(sdf.parse("20/02/2022").getTime()))).thenReturn(bookings).thenReturn(bookings);
		Booking booking=new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0);
		
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(booking,bookingService.saveBooking(booking));
	}
	@Test
	@Order(2)
	public void cancelBookingTest_1() throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		Optional<Booking> booking=Optional.of(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
        when(bookingRepository.findById((long)1)).thenReturn(booking);
        when(bookingRepository.save(booking.get())).thenReturn(booking.get());
        bookingService.cancelbooking(1);
        assertEquals(1,booking.get().getBookingCancelFlag());
		
	}
	
	@Test
	@Order(2)
	public void cancelBookingTest_2() throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		Optional<Booking> booking=Optional.of(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
        when(bookingRepository.findById((long)1)).thenReturn(booking);
        when(bookingRepository.save(booking.get())).thenReturn(booking.get());
        bookingService.cancelbooking(1);
        assertThrows(BusinessException.class,()->bookingService.cancelbooking(0));

		
	}
	@Test
	@Order(3)
	public void saveBookingTest_2() throws ParseException {
		
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		hotels.add(new Hotel(2,"Hotel Surya","Farrukhabad",1000,80,80));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		Booking booking=new Booking(1,2,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0);
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		try {
		when(hotelRepository.findById((long) 1)).thenReturn(Optional.of(hotels.get(0)));
		}
		catch(BusinessException e)
		{
			System.out.println("------\n"+e.getMessage()+"\n-----------");
		}
		try {
			when(hotelRepository.findAllByCity("Farrukhabad")).thenReturn(hotels);
		}
		catch(BusinessException e)
		{
			System.out.println("------\n"+e.getMessage()+"\n-----------");
		}
		
		
		
		
		try {
			when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(1, 1,new java.sql.Date(sdf.parse("20/02/2022").getTime()) , new java.sql.Date(sdf.parse("20/02/2022").getTime()))).thenReturn(bookings).thenReturn(bookings);
		}
		catch(BusinessException e)
		{
			System.out.println("------\n"+e.getMessage()+"\n-----------");
			
		}
		when(bookingRepository.save(booking)).thenReturn(booking);
		
		//assertEquals(booking,bookingService.saveBooking(booking));
		assertThrows(BusinessException.class,()->bookingService.saveBooking(booking));
		
	}
	
	
	
}
