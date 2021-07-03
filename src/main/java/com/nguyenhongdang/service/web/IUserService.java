package com.nguyenhongdang.service.web;

import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.entity.UserEntity;

public interface IUserService {
	UserDTO saveUserMember(UserDTO user);
	UserEntity getByUserName(String username);
}
