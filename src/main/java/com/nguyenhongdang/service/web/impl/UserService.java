package com.nguyenhongdang.service.web.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.constant.GenderConstant;
import com.nguyenhongdang.conveter.UserConveter;
import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.UserRepository;
import com.nguyenhongdang.service.web.IUserService;

@Service("userServiceOfWeb")
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConveter userConveter;

	@Override
	@Transactional
	public UserDTO saveUserMember(UserDTO user) {
		UserEntity entity = userConveter.toEntity(user);
		if (user.getGender() == 1) {
			entity.setGender(GenderConstant.MALE);
		} else if (user.getGender() == 0) {
			entity.setGender(GenderConstant.FEMALE);
		}
		entity.setStatus(1);
		entity = userRepository.save(entity);
		return userConveter.toDTO(entity);
	}

	@Override
	public UserEntity getByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	

}
