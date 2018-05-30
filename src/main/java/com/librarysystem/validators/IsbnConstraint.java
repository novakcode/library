package com.librarysystem.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=IsbnValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsbnConstraint {
		
	String message() default "Invalid ISBN.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
		
	
}
