package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.entity.RoleEntity;
import com.nguyenhongdang.repository.RoleRepository;
import com.nguyenhongdang.service.admin.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<RoleEntity> findAll() {
		return roleRepository.findAll();
	}

}
