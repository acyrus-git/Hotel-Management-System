package com.aditya.hms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Hotels")
public class Hotel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	@Size(min=2, message="Hotel name should be atleast 2 characters")
	private String name;
	@NotEmpty
	private String city;
	@NotNull
	private double price;
	@NotNull
	private int totalRooms;
	private int availableRooms;
	
	
	
	public Hotel(long id, String name, String city, double price, int totalRooms, int availableRooms) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.price = price;
		this.setTotalRooms(totalRooms);
		this.setAvailableRooms(availableRooms);
		
	}
	
	public Hotel() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}

	public int getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}
	
	

}
