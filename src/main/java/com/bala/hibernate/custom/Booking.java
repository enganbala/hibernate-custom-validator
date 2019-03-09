package com.bala.hibernate.custom;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Booking {
	
	@javax.validation.constraints.Future
	private Date date;
	
	@Valid
	@NotNull
	private Car car;

	public Booking(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
