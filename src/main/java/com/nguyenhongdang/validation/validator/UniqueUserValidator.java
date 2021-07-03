package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.UserRepository;
import com.nguyenhongdang.validation.UniqueUserName;

public class UniqueUserValidator  implements ConstraintValidator<UniqueUserName, String> {
	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		UserEntity user = userRepository.findByUsername(value);
		if(user == null) {
			return true;
		}
		return false;
	}
}
