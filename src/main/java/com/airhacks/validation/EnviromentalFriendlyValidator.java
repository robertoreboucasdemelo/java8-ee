package com.airhacks.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.airhacks.enums.EngineType;

public class EnviromentalFriendlyValidator implements ConstraintValidator<EnviromentalFriendly, EngineType> {

	@Override
	public boolean isValid(EngineType engineType, ConstraintValidatorContext context) {
		return engineType == EngineType.ELETRIC;
	}

}
