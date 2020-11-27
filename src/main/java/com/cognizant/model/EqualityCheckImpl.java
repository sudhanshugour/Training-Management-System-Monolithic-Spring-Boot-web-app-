package com.cognizant.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualityCheckImpl implements 
ConstraintValidator<EqualityCheck, Person> {

	@Override
	public boolean isValid(Person person, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if(person.getQid1() == person.getQid2() || person.getQid2() == person.getQid3() || person.getQid1() == person.getQid3() ){
			return false;
		}
		return true;
	}


	
}
