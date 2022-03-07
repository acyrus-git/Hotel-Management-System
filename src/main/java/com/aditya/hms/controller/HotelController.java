package com.aditya.hms.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.hms.exception.BusinessException;
import com.aditya.hms.exception.ControllerException;
import com.aditya.hms.model.Hotel;
import com.aditya.hms.service.HotelService;

@RestController
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/add-hotel")
	public ResponseEntity<?> saveHotel(@RequestBody @Valid Hotel hotel){
		
		try {
		return new ResponseEntity<Hotel>(hotelService.saveHotel(hotel),HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			ControllerException ce=new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get-hotel/{city}")
	public ResponseEntity<?> getHotelByCity(@PathVariable String city){
		
		
		return new ResponseEntity<List<Hotel>>(hotelService.getHotelsByCity(city),HttpStatus.OK);
	} 
	
	@GetMapping("/get-hotel")
	public ResponseEntity<?> getHotels(@RequestParam(name="city",required=false, defaultValue="Unknown") String city, 
			@RequestParam(name="checkIn", required=false) @DateTimeFormat(pattern="dd/MM/yyyy")Date checkIn,
			@RequestParam(name="checkOut", required=false) @DateTimeFormat(pattern="dd/MM/yyyy")Date checkOut){
		
		try {
		return new ResponseEntity<List<Hotel>>(hotelService.getAllHotels(city, checkIn, checkOut),HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			ControllerException ce=new ControllerException(e.getErrorCode(),e.getMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		}
}
