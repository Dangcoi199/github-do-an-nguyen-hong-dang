package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.ChatLieuMatEntity;

public interface IChatLieuMatRepository extends JpaRepository<ChatLieuMatEntity, Long> {
	ChatLieuMatEntity findByName(String name);
	ChatLieuMatEntity findByCode(String code);
}
