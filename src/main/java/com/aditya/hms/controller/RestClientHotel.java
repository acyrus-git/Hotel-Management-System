package com.aditya.hms.controller;

import com.aditya.hms.model.Hotel;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClientHotel {

    public static final String GET_All_HOTELS_API="http://localhost:8080/get-hotel";
    public static final String Get_HOTELS_BY_CITY="http://localhost:8080/get-hotel/{city}";
    public static final String SAVE_HOTEL="http://localhost:8080/add-hotel";
    static RestTemplate restTemplate=new RestTemplate();
    public static void main(String [] args)
    {
       // callGetAllHotels();
      //callGetHotelByCity();
        callSaveHotel();
    }
    public static void callGetAllHotels(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Query parameters
        /*UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_All_HOTELS_API)
                // Add query parameter
                .queryParam("city", "Farrukhabad")
                .queryParam("checkIn", "16/02/2022")
                .queryParam("checkout","17/02/2022");*/
        String city="Farrukhabad";
        String checkIn="16/02/2022";
        String checkOut="17/02/2022";
        String url=GET_All_HOTELS_API+"?city="+city+"&checkIn="+checkIn+"&checkOut="+checkOut;

        HttpEntity<String> httpEntity=new HttpEntity<>("parameters",headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        if(result.getStatusCode()==(HttpStatus.OK)) {
            System.out.println(result.getBody());
        }
        else
        {
            System.out.println("Couldn't get Hotels");

        }


    }
    public static void callGetHotelByCity(){
        Map<String,String> param= new HashMap<>();
        param.put("city","Farrukhabad");
        ResponseEntity<String> result= restTemplate.getForEntity(Get_HOTELS_BY_CITY,String.class,param);
        if(result.getStatusCode()==(HttpStatus.OK)) {
            System.out.println(result.getBody());
        }
        else
        {
            System.out.println("Couldn't get Hotels");

        }
    }
    public static void callSaveHotel(){
        Hotel hotel=new Hotel();
        hotel.setName("Hotel Silchar");
        hotel.setAvailableRooms(50);
        hotel.setTotalRooms(50);
        hotel.setCity("Silchar");
        hotel.setPrice(1500);
        ResponseEntity<Hotel> result=restTemplate.postForEntity(SAVE_HOTEL,hotel,Hotel.class);
        if(result.getStatusCode()==(HttpStatus.CREATED)) {
            System.out.println(result.getBody());
        }
        else
        {
            System.out.println("Couldn't save Hotels");

        }
    }

}
