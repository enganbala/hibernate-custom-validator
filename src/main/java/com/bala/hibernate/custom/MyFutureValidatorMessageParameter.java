package com.bala.hibernate.custom;

import java.time.Instant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class MyFutureValidatorMessageParameter implements ConstraintValidator<Future, Instant> {

	public void initialize(Future constraintAnnotation) {
	}

	public boolean isValid(Instant value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		HibernateConstraintValidatorContext hibernateContext = context
				.unwrap(HibernateConstraintValidatorContext.class);

		Instant now = Instant.now(context.getClockProvider().getClock());

		if (!value.isAfter(now)) {
			hibernateContext.disableDefaultConstraintViolation();
			hibernateContext.addMessageParameter("now", now).buildConstraintViolationWithTemplate("Must be after {now}")
					.addConstraintViolation();

			return false;
		}

		return true;
	}
}