package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.entity.UserEntity;

public interface IUserService {
	UserDTO save(UserDTO user);
	UserDTO update(UserDTO user);
	List<UserEntity> findUserByStatus();
	void deleteUser(long[] ids);
	UserEntity getOne(long id);
	
}
