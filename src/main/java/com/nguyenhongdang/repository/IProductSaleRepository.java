package com.nguyenhongdang.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.ProductSaleEntity;

public interface IProductSaleRepository extends JpaRepository<ProductSaleEntity, Long>{
	List<ProductSaleEntity> findByEndAfter(Date dateBegin);
}
