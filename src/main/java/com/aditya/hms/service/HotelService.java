package com.aditya.hms.service;

import java.util.Date;
import java.util.List;

import com.aditya.hms.model.Hotel;

public interface HotelService {
	
	Hotel saveHotel(Hotel hotel);

	List<Hotel>getAllHotels(String city,Date checkIn,Date checkOut);
	
	List<Hotel>getHotelsByCity(String city);
}
