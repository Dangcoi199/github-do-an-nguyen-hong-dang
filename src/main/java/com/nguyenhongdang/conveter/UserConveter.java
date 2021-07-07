package com.nguyenhongdang.conveter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.entity.UserEntity;

@Component
public class UserConveter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public UserDTO toDTO(UserEntity entity) {
		UserDTO model = new UserDTO();
		model.setUsername(entity.getUsername());
		model.setEmail(entity.getEmail());
		model.setStatus(entity.getStatus());
		model.setPassword(entity.getPassword());
		model.setFullname(entity.getFullname());
		model.setYearOfBirth(entity.getYearOfBirth());
		return model;
	}
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity =  new UserEntity();
		entity.setUsername(dto.getUsername());
		entity.setFullname(dto.getFullname());
		entity.setYearOfBirth(dto.getYearOfBirth());
		entity.setEmail(dto.getEmail());
		entity.setStatus(dto.getStatus());
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		return entity;
	}
	public UserEntity toEntity(UserEntity entity ,UserDTO dto) {
		entity.setEmail(dto.getEmail());
		entity.setFullname(dto.getFullname());
		entity.setYearOfBirth(dto.getYearOfBirth());
		if(dto.getPassword().length() < 25) {
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		}else {
			entity.setPassword(dto.getPassword());
		}
		return entity;
	}
}
