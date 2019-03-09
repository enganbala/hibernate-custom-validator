package com.bala.hibernate.custom;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class MyFutureValidator implements ConstraintValidator<Future, Date> {

    public void initialize(Future constraintAnnotation) {}

    public boolean isValid(Date value, ConstraintValidatorContext context) {
    	System.out.println("Calling MyFutureValidator Is Valid Method");
        Date now = GregorianCalendar.getInstance().getTime();

        if ( value.before( now ) ) {
            HibernateConstraintValidatorContext hibernateContext =
                context.unwrap( HibernateConstraintValidatorContext.class );

            hibernateContext.disableDefaultConstraintViolation();
            hibernateContext.addExpressionVariable( "now", now )
                .buildConstraintViolationWithTemplate( "Must be after ${now}" )
                .addConstraintViolation();

            return false;
        }

        return true;
    }
}