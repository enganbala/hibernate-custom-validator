package com.bala.hibernate.custom;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Locale;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstructorDescriptor;
import javax.validation.metadata.MethodDescriptor;
import javax.validation.metadata.ParameterDescriptor;
import javax.validation.metadata.PropertyDescriptor;
import javax.validation.metadata.ReturnValueDescriptor;
import javax.validation.spi.ValidationProvider;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.DefaultClockProvider;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

/**
 * A helper providing useful functions for setting up validators.
 *
 * @author Hardy Ferentschik
 * @author Gunnar Morling
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 */
public final class ValidatorUtil {

	/**
	 * Private constructor in order to avoid instantiation.
	 */
	private ValidatorUtil() {
	}



	/**
	 * Returns the {@code Configuration} object for Hibernate Validator. This method also sets the default locale to
	 * english.
	 *
	 * @return an instance of {@code Configuration} for Hibernate Validator.
	 */
	public static HibernateValidatorConfiguration getConfiguration() {
		return getConfiguration( HibernateValidator.class, Locale.ENGLISH );
	}

	/**
	 * Returns the {@code Configuration} object for Hibernate Validator. This method also sets the default locale to
	 * the given locale.
	 *
	 * @param locale The default locale to set.
	 *
	 * @return an instance of {@code Configuration} for Hibernate Validator.
	 */
	public static HibernateValidatorConfiguration getConfiguration(Locale locale) {
		return getConfiguration( HibernateValidator.class, locale );
	}

	/**
	 * Returns the {@code Configuration} object for the given validation provider type. This method also sets the
	 * default locale to english.
	 *
	 * @param type The validation provider type.
	 *
	 * @return an instance of {@code Configuration}.
	 */
	public static <T extends Configuration<T>, U extends ValidationProvider<T>> T getConfiguration(Class<U> type) {
		return getConfiguration( type, Locale.ENGLISH );
	}

	/**
	 * Returns the {@code Configuration} object for the given validation provider type. This method also sets the
	 * default locale to the given locale.
	 *
	 * @param type The validation provider type.
	 * @param locale The default locale to set.
	 *
	 * @return an instance of {@code Configuration}.
	 */
	public static <T extends Configuration<T>, U extends ValidationProvider<T>> T getConfiguration(Class<U> type, Locale locale) {
		Locale.setDefault( locale );
		return Validation.byProvider( type ).configure();
	}


}