package com.bala.hibernate.custom;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, Car> {

    private static final Map<Integer, String> suggestedCars = newHashMap();

    static {
        suggestedCars.put( 2, "Chevrolet Corvette" );
        suggestedCars.put( 3, "Toyota Volta" );
        suggestedCars.put( 4, "Maserati GranCabrio" );
        suggestedCars.put( 5, " Mercedes-Benz E-Class" );
    }

    
    
    public void initialize(ValidPassengerCount constraintAnnotation) {
    }

    
    public boolean isValid(Car car, ConstraintValidatorContext context) {
    	System.out.println("Calling Is Valid Method");
        if ( car == null ) {
            return true;
        }

        int passengerCount = car.getPassengers().size();
        if ( car.getSeatCount() >= passengerCount ) {
            return true;
        }
        else {

            if ( suggestedCars.containsKey( passengerCount ) ) {
                HibernateConstraintValidatorContext hibernateContext = context.unwrap(
                        HibernateConstraintValidatorContext.class
                );
                hibernateContext.withDynamicPayload( suggestedCars.get( passengerCount ) );
            }
            return false;
        }
    }
}