package com.aditya.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.hms.model.Booking;
import com.aditya.hms.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long>{

	List<Hotel> findAllByCity(String city);

	
	
	
	

}
