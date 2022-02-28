package com.aditya.hms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.hms.exception.BusinessException;
import com.aditya.hms.model.Booking;
import com.aditya.hms.model.Hotel;
import com.aditya.hms.repository.BookingRepository;
import com.aditya.hms.repository.HotelRepository;
import com.aditya.hms.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	private static ReentrantLock lock= new ReentrantLock();

	Logger logger=LoggerFactory.getLogger(HotelServiceImpl.class);
	@Override
	public List<Hotel> getAllHotels(String city, Date checkIn, Date checkOut) {
		// TODO Auto-generated method stub
		List<Hotel> availableHotels=new ArrayList<>();
		try {
		List<Hotel> hotels=getHotelsByCity(city);
		
		
		for(int i=0;i<hotels.size();i++)
		{
		
			List<Booking> bookings=bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(hotels.get(i).getId(),0,checkOut,checkIn);
			//testing
			//System.out.println(bookings.size());
			int r=hotels.get(i).getTotalRooms()-bookings.size();
			hotels.get(i).setAvailableRooms(r);
		}
		//for testing
		//System.out.println(hotels.get(0).getAvailableRooms());
		for(int i=0;i<hotels.size();i++)
		{
			if(hotels.get(i).getAvailableRooms()>0)
				availableHotels.add(hotels.get(i));
		}
		}
		catch(BusinessException e)
		{
			throw new BusinessException(e.getErrorCode(),e.getMessage());
		}
		catch(Exception e)
		{
			logger.error("error while fetching hotels");
			throw new BusinessException("601","Something wnt wrong in service layer while fetching hotels");
		}
		return availableHotels;
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		Hotel h=null;
		lock.lock();
		try {
			h=hotelRepository.save(hotel);
			
		}
		catch(Exception e)
		{
			logger.error("error while saving hotel");
			throw new BusinessException("602","Couldn't add hotel in database");
		}
		finally {
			lock.unlock();
		}
		return h;
		
	}

	@Override
	public List<Hotel> getHotelsByCity(String city) {
		
		List<Hotel> hotelList=new ArrayList();
		try {
		hotelList= hotelRepository.findAllByCity(city);
		}
		catch(Exception e)
		{
			throw new BusinessException("603","Something went wrong in service layer");
		}
		if(hotelList.isEmpty())
		{
			logger.info("no hotels present for given city");
			throw new BusinessException("604","Couldn't find hotels for the given city");
		}
		return hotelList;
	}

}
