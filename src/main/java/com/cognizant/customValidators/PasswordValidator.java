package com.cognizant.customValidators;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cognizant.entity.ResetPassword;

@Service
public class PasswordValidator implements Validator {
	
	public PasswordValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Class<?> cla) {
		// TODO Auto-generated method stub
		return ResetPassword.class.equals(cla);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		ResetPassword reset = (ResetPassword) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password Cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirm", "", "Confirm Password Cannot be empty");
		if(!reset.getPassword().equals(reset.getConfirm())){
			errors.rejectValue("confirm", "", "Password and Confirm Password does not match");
		}
	}

	

}
