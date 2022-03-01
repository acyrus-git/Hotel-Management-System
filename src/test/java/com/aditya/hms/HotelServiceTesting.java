package com.aditya.hms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.aditya.hms.exception.BusinessException;
import com.aditya.hms.model.Booking;
import com.aditya.hms.model.Hotel;
import com.aditya.hms.repository.BookingRepository;
import com.aditya.hms.repository.HotelRepository;
import com.aditya.hms.service.HotelService;
import com.aditya.hms.service.impl.HotelServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {HotelServiceTesting.class})
public class HotelServiceTesting {
	
	@Mock
	private HotelRepository hotelRepository;
	
	@Mock
	private BookingRepository bookingRepository;
	
	@InjectMocks
	private HotelService hotelService=new HotelServiceImpl();
	
	private List<Hotel>hotels=new ArrayList();
	private List<Booking>bookings=new ArrayList();
	
	@Test
	@Order(1)
	public void saveHotelTest()
	{
		Hotel hotel=new Hotel();
		hotel.setName("Hotel Galaxy");
		hotel.setCity("Kanpur");
		hotel.setAvailableRooms(150);
		hotel.setPrice(1000);
		hotel.setTotalRooms(150);
		
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		
		
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		
		assertEquals(hotel,hotelService.saveHotel(hotel));

	}
	
	@Test
	@Order(2)
	public void getAllHotelsTest_1() throws ParseException {
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		when(hotelRepository.findAllByCity("Farrukhabad")).thenReturn(hotels);
		when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(1, 0,new java.sql.Date(sdf.parse("20/02/2022").getTime()) , new java.sql.Date(sdf.parse("20/02/2022").getTime()))).thenReturn(bookings);
		assertEquals(2,hotelService.getAllHotels("Farrukhabad", new java.sql.Date(sdf.parse("20/02/2022").getTime()), new java.sql.Date(sdf.parse("21/02/2022").getTime())).size());
	}
	
	@Test
	@Order(3)
	public void getAllHotelsTest_2() throws ParseException {
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		bookings.add(new Booking(1,1,new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()),0));
		try {
			when(hotelRepository.findAllByCity("Farrukhabad")).thenReturn(hotels);
		}
		catch(Exception e)
		{
			System.out.println("------\n"+e.getMessage()+"\n-----------");
		}
		
		when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(1, 0,new java.sql.Date(sdf.parse("20/02/2022").getTime()) , new java.sql.Date(sdf.parse("20/02/2022").getTime()))).thenReturn(bookings);
	
		//assertEquals(2,hotelService.getAllHotels("Farrukhabad", new java.sql.Date(sdf.parse("20/02/2022").getTime()), new java.sql.Date(sdf.parse("21/02/2022").getTime())).size());
		assertThrows(BusinessException.class,()->hotelService.getAllHotels("Farrukh", new java.sql.Date(sdf.parse("20/02/2022").getTime()), new java.sql.Date(sdf.parse("21/02/2022").getTime())));
	}
	@Test
	@Order(4)
	public void getAllHotelsTest_3() throws ParseException {
		hotels.add(new Hotel((long)1,"Hotel Anand","Farrukhabad",1000,5,4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		bookings.add(new Booking((long)1,(long)1,new java.util.Date(sdf.parse("20/02/2022").getTime()),new java.util.Date(sdf.parse("21/02/2022").getTime()),0));
		bookings.add(new Booking((long)2,(long)1,new java.util.Date(sdf.parse("20/02/2022").getTime()),new java.util.Date(sdf.parse("21/02/2022").getTime()),0));
		bookings.add(new Booking((long)3,(long)1,new java.util.Date(sdf.parse("20/02/2022").getTime()),new java.util.Date(sdf.parse("21/02/2022").getTime()),0));
		bookings.add(new Booking((long)4,(long)1,new java.util.Date(sdf.parse("20/02/2022").getTime()),new java.util.Date(sdf.parse("21/02/2022").getTime()),0));
		bookings.add(new Booking((long)5,(long)1,new java.util.Date(sdf.parse("20/02/2022").getTime()),new java.util.Date(sdf.parse("21/02/2022").getTime()),0));
		when(hotelRepository.findAllByCity("Farrukhabad")).thenReturn(hotels);
		when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(1, 0,new java.sql.Date(sdf.parse("20/02/2022").getTime()) , new java.sql.Date(sdf.parse("21/02/2022").getTime()))).thenReturn(bookings);
		int siz=hotelService.getAllHotels("Farrukhabad", new java.sql.Date(sdf.parse("20/02/2022").getTime()), new java.sql.Date(sdf.parse("21/02/2022").getTime())).size();
		//System.out.println(siz);
		assertEquals(1,siz);
	}

}
