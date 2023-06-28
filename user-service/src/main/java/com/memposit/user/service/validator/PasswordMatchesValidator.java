package com.memposit.user.service.validator;

import com.memposit.user.service.model.ActionRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The Password matches validator.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ActionRequest> {

	@Override
	public boolean isValid(final ActionRequest user, final ConstraintValidatorContext context) {
		return user.getPassword().equals(user.getMatchingPassword());
	}

}
