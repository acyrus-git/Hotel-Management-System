package com.aditya.hms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aditya.hms.controller.HotelController;
import com.aditya.hms.model.Hotel;
import com.aditya.hms.service.HotelService;

@SpringBootTest(classes= {HotelControllerTesting.class})
public class HotelControllerTesting {
	
	@Mock
	private HotelService hotelService;
	
	@InjectMocks
	private HotelController hotelController= new HotelController();
	
	List<Hotel>hotels=new ArrayList<>();
	
	@Test
	public void saveHotelTest() {
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		Hotel hotel=new Hotel(2,"Hotel Surya","Farrukhabad",1000,80,80);

		when(hotelService.saveHotel(hotel)).thenReturn(hotel);
		
		ResponseEntity<?>res=hotelController.saveHotel(hotel);
		assertEquals(res.getStatusCode(),HttpStatus.CREATED);
		assertEquals(res.getBody(),hotel);
	}
	
	@Test
	public void getHotelsTest() throws ParseException
	{
		hotels.add(new Hotel(1,"Hotel Anand","Farrukhabad",1000,50,50));
		hotels.add(new Hotel(2,"Hotel Surya","Farrukhabad",1000,80,80));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		when(hotelService.getAllHotels("Farrukhabad", new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()))).thenReturn(hotels);

		ResponseEntity<List<Hotel>>res=(ResponseEntity<List<Hotel>>) hotelController.getHotels("Farrukhabad", new java.sql.Date(sdf.parse("20/02/2022").getTime()),new java.sql.Date(sdf.parse("21/02/2022").getTime()));
		assertEquals(res.getStatusCode(),HttpStatus.OK);
		assertEquals(res.getBody().size(),2);
	}

}
