package com.librarysystem.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<IsbnConstraint, String>{

	@Override
	public void initialize(IsbnConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
			final String isbnRegexV1 = "\\d{3}-\\d{1}-\\d{6}-\\d{2}-\\d{1}";
			final String isbnRegexV2 = "\\d{3}-\\d{1}-\\d{5}-\\d{3]-\\d{1}";
		
			return value.matches(isbnRegexV1) || value.matches(isbnRegexV2);
			
	}
	
}
