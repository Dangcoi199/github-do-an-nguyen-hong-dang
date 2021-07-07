package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.constant.GenderConstant;
import com.nguyenhongdang.constant.LoaiTaiKhoanConstant;
import com.nguyenhongdang.conveter.UserConveter;
import com.nguyenhongdang.dto.UserDTO;
import com.nguyenhongdang.entity.RoleEntity;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.RoleRepository;
import com.nguyenhongdang.repository.UserRepository;
import com.nguyenhongdang.service.admin.IUserService;
@Service("userServiceOfAdmin")
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserConveter userConveter;
	@Autowired
	private EntityManager entityManager;
	@Override
	@Transactional
	public UserDTO save(UserDTO user) {
		UserEntity entity = new UserEntity();
		List<RoleEntity> roles = roleRepository.findByCode(user.getRoleCode());
		entity = userConveter.toEntity(user);
		entity.setStatus(1);
		if(user.getGender() == 1) {
			entity.setGender(GenderConstant.MALE);
		}else if(user.getGender() ==0) {
			entity.setGender(GenderConstant.FEMALE);
		}
		entity.setLoaiTaiKhoan(LoaiTaiKhoanConstant.NORMAL);
		entity.setRoles(roles);
		entity = userRepository.save(entity);
		return userConveter.toDTO(entity);
	}
	@Override
	@Transactional
	public void deleteUser(long[] ids) {
		for(long id : ids) {
			UserEntity oldUser = userRepository.getOne(id);
			oldUser.setStatus(0);
			userRepository.save(oldUser);
		}	
	}
	@Override
	public List<UserEntity> findUserByStatus() {
		Session session = entityManager.unwrap(Session.class);
		String hql ="FROM UserEntity u WHERE u.status = 1";
		Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
		return query.getResultList();
	}
	@Override
	public UserEntity getOne(long id) {
		return userRepository.getOne(id);
	}
	@Override
	@Transactional
	public UserDTO update(UserDTO user) {
		UserEntity entity = new UserEntity();
		List<RoleEntity> roles = roleRepository.findByCode(user.getRoleCode());
		UserEntity oldUser = userRepository.getOne(user.getId());
	   	entity = userConveter.toEntity(oldUser, user);
		entity.setRoles(roles);
		if(user.getGender() == 1) {
			entity.setGender(GenderConstant.MALE);
		}else if(user.getGender() ==0) {
			entity.setGender(GenderConstant.FEMALE);
		}
		entity = userRepository.save(entity);
		return userConveter.toDTO(entity);
	}
	
}
