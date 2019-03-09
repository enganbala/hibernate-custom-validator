package com.bala.hibernate.custom;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.junit.Test;

public class ValidateCarTest {
	
	private static Validator validator;

	@Test
	public void testValidPassengerCount() {
		Car car = new Car( "Toyota", "DD-AB-123", 1 );
		car.addPassenger( new Person() );
		car.addPassenger( new Person() );
		car.addPassenger( new Person() );
		
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	      
		Set<ConstraintViolation<Car>> constraintViolations = validator.validate( car );

		assertEquals( 1, constraintViolations.size() );
		for (ConstraintViolation<Car> con : constraintViolations){
			System.out.println(con.getMessage());
		}
		
		

		ConstraintViolation<Car> constraintViolation = constraintViolations.iterator().next();
		
		@SuppressWarnings("unchecked")
		HibernateConstraintViolation<Car> hibernateConstraintViolation = constraintViolation.unwrap(
		        HibernateConstraintViolation.class
		);
		String suggestedCar = hibernateConstraintViolation.getDynamicPayload( String.class );
		assertEquals( "Toyota Volta", suggestedCar );

	}

}
