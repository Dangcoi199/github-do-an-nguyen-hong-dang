package com.nguyenhongdang.api.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.service.web.IUserService;

@RestController("userAPIOfWeb")
public class UserAPI {
	@Autowired
	private IUserService userService;

	@PostMapping("/userMembers")
	public UserDTO saveUserMember(@Valid @RequestBody UserDTO user) {
		return userService.saveUserMember(user);
	}
}
