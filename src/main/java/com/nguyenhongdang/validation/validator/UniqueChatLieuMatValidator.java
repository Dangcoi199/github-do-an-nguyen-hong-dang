package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.ChatLieuMatEntity;
import com.nguyenhongdang.repository.IChatLieuMatRepository;
import com.nguyenhongdang.validation.UniqueChatLieuMat;

public class UniqueChatLieuMatValidator implements ConstraintValidator<UniqueChatLieuMat, String>{

	@Autowired
	private IChatLieuMatRepository chatLieuMatRepository;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		ChatLieuMatEntity entity = chatLieuMatRepository.findByName(value);
		if(entity ==  null) {
			return true;
		}
		return false;
	}

}
