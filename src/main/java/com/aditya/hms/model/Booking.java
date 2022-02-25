package com.aditya.hms.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private long hotelId;
	@NotEmpty
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date checkIn;
	@NotEmpty
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date checkOut;
	@NotEmpty
	private int bookingCancelFlag;
	
	
	public Booking() {
		}
	
	public Booking(long id, long hotelId, Date checkIn, Date checkOut, int bookingCancelFlag) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.bookingCancelFlag = bookingCancelFlag;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public int getBookingCancelFlag() {
		return bookingCancelFlag;
	}
	public void setBookingCancelFlag(int bookingCancelFlag) {
		this.bookingCancelFlag = bookingCancelFlag;
	}
	
	
		

}
