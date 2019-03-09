package com.bala.hibernate.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.ConstraintDefinitionContext;
import org.hibernate.validator.cfg.context.TypeConstraintMappingContext;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorDescriptor;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.junit.Test;

public class CustomFutureTest {
	private static Validator validator;

	@Test
	public void testCustomFuture() {
		Booking booking = new Booking(Date.from(Calendar.getInstance().toInstant()));

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();


		Set<ConstraintViolation<Booking>> constraintViolations = validator.validate(booking);

//		assertEquals(1, constraintViolations.size());
		for (ConstraintViolation<Booking> con : constraintViolations) {
			System.out.println(con.getMessage());
			System.out.println(con.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
			System.out.println("Field "+ con.getPropertyPath());
			//assertNotEquals("must be a future date", con.getMessage());
		}
		
		ConstraintViolation<Booking> constraintViolation = constraintViolations.iterator().next();
		

		@SuppressWarnings("unchecked")
		HibernateConstraintViolation<Car> hibernateConstraintViolation = constraintViolation
				.unwrap(HibernateConstraintViolation.class);
		String customMessage = hibernateConstraintViolation.getDynamicPayload(String.class);
		assertNotEquals("must be a future date", customMessage);

	}
}
