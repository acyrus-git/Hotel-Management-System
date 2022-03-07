package com.aditya.hms.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.hms.exception.BusinessException;
import com.aditya.hms.exception.ControllerException;
import com.aditya.hms.model.Booking;
import com.aditya.hms.service.BookingService;
import com.aditya.hms.service.impl.BookingServiceImpl;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	Logger logger=LoggerFactory.getLogger(BookingServiceImpl.class);
	
	@PostMapping("/add-booking")
	public ResponseEntity<?> saveBooking(@RequestBody /*@Valid*/ Booking booking){
		
		logger.info("Recieved a request to save booking with id "+ booking.getId()+ "for hotel with hotel id "+booking.getHotelId());
		
		try {
		return new ResponseEntity<Booking>(bookingService.saveBooking(booking),HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			ControllerException ce=new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			ControllerException ce=new ControllerException("621","Something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/get-bookings/{id}")
	public ResponseEntity<?> getAllBookings(@PathVariable long id)
	{
		return new ResponseEntity<List<Booking>>(bookingService.getAllBookings(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel-booking/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable long id){

		logger.info("Recieved a request to cancel booking with id "+ id);
		try {
			System.out.println("yo");
		return new ResponseEntity<String>(bookingService.cancelbooking(id),HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			ControllerException ce=new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			ControllerException ce=new ControllerException("622","Something went wrong in controller layer");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
}
