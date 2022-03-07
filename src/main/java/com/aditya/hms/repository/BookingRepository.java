package com.aditya.hms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.hms.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

	List<Booking> findAllByHotelIdAndBookingCancelFlagAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(long id, int i,
			Date checkOut, Date checkIn);
	List<Booking> findAllByHotelId(long id);
} 
