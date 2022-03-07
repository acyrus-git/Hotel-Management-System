package com.aditya.hms.controller;

import com.aditya.hms.model.Booking;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClientBooking {

    public static final String GET_ALL_BOOKINGS="http://localhost:8080/get-bookings/{id}";
    public static final String SAVE_BOOKING="http://localhost:8080/add-booking";
    public static final String CANCEL_BOOKING="http://localhost:8080/cancel-booking/{id}";
    static RestTemplate restTemplate=new RestTemplate();
    public static void main(String [] args) throws ParseException {
        //callGetAllBookings();
        //callSaveBooking();
       callCancelBooking();
    }

    public static void callGetAllBookings(){
        Map<String,Integer> params=new HashMap<>();
        params.put("id",1);
        ResponseEntity<String> result = restTemplate.getForEntity(GET_ALL_BOOKINGS, String.class, params);
        if(result.getStatusCode()==(HttpStatus.OK)) {
            System.out.println(result.getBody());
        }
        else
        {
            System.out.println("Couldn't get bookings");

        }
    }

    public static void callSaveBooking() throws ParseException {
        Booking booking=new Booking();
        booking.setHotelId(3);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        booking.setCheckIn(new java.sql.Date(sdf.parse("17/02/2022").getTime()));
        booking.setCheckOut(new java.sql.Date(sdf.parse("18/02/2022").getTime()));
        booking.setBookingCancelFlag(0);
        ResponseEntity<Booking> result = restTemplate.postForEntity(SAVE_BOOKING, booking, Booking.class);
        if(result.getStatusCode()==(HttpStatus.CREATED)) {
            System.out.println(result.getBody());
        }
        else
        {
            System.out.println("Couldn't save booking");

        }
    }
    public static void callCancelBooking(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity=new HttpEntity<>("parameters",headers);
        Map<String,String> param=new HashMap<>();
        param.put("id","3");

        //restTemplate.delete(CANCEL_BOOKING,param);
        ResponseEntity<String> result = restTemplate.exchange(CANCEL_BOOKING, HttpMethod.DELETE, requestEntity, String.class,param);
        if(result.getStatusCode()==(HttpStatus.OK)) {
            System.out.println(result.getBody());
        }
        else
        {
            System.out.println("Couldn't cancel booking");

        }
    }
}
