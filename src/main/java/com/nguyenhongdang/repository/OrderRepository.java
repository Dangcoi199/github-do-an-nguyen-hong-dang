package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.OrderInfoEntity;

public interface OrderRepository extends JpaRepository<OrderInfoEntity, Long> {

}
