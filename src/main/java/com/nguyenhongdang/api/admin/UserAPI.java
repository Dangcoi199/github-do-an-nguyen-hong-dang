package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.service.admin.IUserService;

@RestController("userAPIOfAdmin")
public class UserAPI {
	@Autowired
	private IUserService userService;


	@PostMapping("/users")
	public UserDTO save(@Valid @RequestBody UserDTO user) {
		return userService.save(user);
	}

	@DeleteMapping("/users")
	public void deleteUser(@RequestBody long[] ids) {
		userService.deleteUser(ids);
	}

	@PutMapping("/users")
	public UserDTO update(@RequestBody UserDTO user) {
		return userService.update(user);
	}
}
