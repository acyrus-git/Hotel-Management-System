package com.aditya.hms.service.impl;

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
import com.aditya.hms.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	private static ReentrantLock lock=new ReentrantLock();
	
	Logger logger=LoggerFactory.getLogger(BookingServiceImpl.class);
	
	@Override
	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		
		if(booking==null)
			throw new BusinessException("607","Booking object is null");
		Hotel h= new Hotel();
			h=hotelRepository.findById(booking.getHotelId()).orElseThrow(()->new BusinessException("606","No hotel found for given id"));
			List<Booking> bookings=bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(h.getId(),0,booking.getCheckOut(),booking.getCheckIn());
			int availableRooms=h.getTotalRooms()-bookings.size();
			if(availableRooms<1) {
				logger.info("There are no rooms for the given hotel");
				throw new BusinessException("608","No rooms available for the given hotel");
			}
			Booking newBooking=null;
			lock.lock();
			try {
				newBooking=bookingRepository.save(booking);
			}
			catch(Exception e)
			{
				logger.error("There was an error while saving booking details in data base");
				throw new BusinessException("609","Something went wrong in Business layer");
			}
			finally {
				lock.unlock();
			}
		return newBooking;
	}
	
	

	@Override
	public String cancelbooking(long id) {
		Booking booking=bookingRepository.findById(id).orElseThrow(()->new BusinessException("610","No booking present for given id"));
		
		
		if(booking.getBookingCancelFlag()==1)
			throw new BusinessException("611","Booking already cancelled for the given id");
		
			booking.setBookingCancelFlag(1);
			String s="Booking cancelled with bookingid "+booking.getId();
			lock.lock();
			try {
			bookingRepository.save(booking);
			}
			catch(Exception e)
			{
				s="Booking not cancelled";
				logger.error(s);
				throw new BusinessException("612","Something went wrong in service layer"); 
			}
			finally {
				lock.unlock();
			}
			return s;
			}

	
}
