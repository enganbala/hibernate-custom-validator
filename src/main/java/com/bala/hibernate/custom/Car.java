package com.bala.hibernate.custom;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidPassengerCount
public class Car {

	@NotNull
	private String manufacturer;

	@NotNull
	@Size(min = 2, max = 14)
	private String licensePlate;

	@Min(2)
	private int seatCount;

	
	private List<Person> passengers =new ArrayList<Person>();
	
	public Car(int seatCount) {
		this.seatCount = seatCount;
	}
	
	

	public Car(String manufacturer, String licencePlate, int seatCount) {
		this.manufacturer = manufacturer;
		this.licensePlate = licencePlate;
		this.seatCount = seatCount;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public List getPassengers() {
		return passengers;
	}

	public void setPassengers(List passengers) {
		this.passengers = passengers;
	}



	public void addPassenger(Person person) {
		passengers.add(person);
		
	}

}