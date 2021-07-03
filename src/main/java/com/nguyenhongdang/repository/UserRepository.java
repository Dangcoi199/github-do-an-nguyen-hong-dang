package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
	UserEntity findByUsernameAndStatus(String username,int status);
}
