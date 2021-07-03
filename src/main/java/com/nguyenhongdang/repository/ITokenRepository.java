package com.nguyenhongdang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.TokenEntity;

public interface ITokenRepository extends JpaRepository<TokenEntity, String>{
	List<TokenEntity> findByProductId(long productId);
}
