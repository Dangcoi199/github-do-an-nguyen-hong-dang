package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.OrderInfoEntity;
import com.nguyenhongdang.entity.SanPhamEntity;

public interface IOrderInfoService {
	OrderInfoEntity add(OrderInfoEntity entity);
	List<OrderInfoEntity> findAll();
	OrderInfoEntity getOne(long id);
	List<SanPhamEntity> getProductById(OrderInfoEntity orderInfo);
}
