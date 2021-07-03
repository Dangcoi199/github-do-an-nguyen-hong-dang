package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.LoaiDayEntity;

public interface ILoaiDayRepository extends JpaRepository<LoaiDayEntity, Long> {
	LoaiDayEntity findByLoaiDay(String loaiDay);
	LoaiDayEntity findByCode(String code);
}
