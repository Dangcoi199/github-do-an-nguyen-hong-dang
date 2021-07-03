package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.UniqueUserName;

import lombok.Data;

@Data
public class UserDTO extends AbstractDTO<UserDTO> {
	@UniqueUserName
	private String username;
	private String password;
	private String fullname;
	private String yearOfBirth;
	private Integer gender;
	private String email;
	private Integer status;
	private String roleCode;
}
