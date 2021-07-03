package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.SliderEntity;

public interface ISliderRepository extends JpaRepository<SliderEntity, Long> {
}
