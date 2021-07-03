package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.ChatLieuVoEntity;

public interface IChatLieuVoRepository extends JpaRepository<ChatLieuVoEntity, Long> {
	ChatLieuVoEntity findByMaterial(String material);
	ChatLieuVoEntity findByCode(String code);
}
