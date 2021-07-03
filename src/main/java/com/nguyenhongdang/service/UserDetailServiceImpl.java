package com.nguyenhongdang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepo.findByUsernameAndStatus(username,1);
		if (entity == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(entity);
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	public UserDetails loadUserById(Long id) {
		UserEntity entity = userRepo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return new CustomUserDetails(entity);
	}

}
