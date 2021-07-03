package com.nguyenhongdang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.TinTucEntity;

public interface ITinTucRepository extends JpaRepository<TinTucEntity,Long> {
	TinTucEntity findByCode(String code);
	List<TinTucEntity> findByDanhMuc(String danhMuc);
}
