package com.cognizant.model;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = EqualityCheckImpl.class)
public @interface EqualityCheck {
	String message() default "All The Security Questions Should Be Different";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
