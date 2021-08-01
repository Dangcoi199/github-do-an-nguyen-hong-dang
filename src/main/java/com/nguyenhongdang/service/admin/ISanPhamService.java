package com.nguyenhongdang.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nguyenhongdang.dto.DiscountDTO;
import com.nguyenhongdang.dto.SanPhamDTO;
import com.nguyenhongdang.dto.UpdateDiscountDTO;
import com.nguyenhongdang.entity.SanPhamEntity;

public interface ISanPhamService {
	// Lấy sản phẩm theo Id
	SanPhamEntity getOne(long id);

	// Lấy toàn bộ sản phẩm
	List<SanPhamEntity> findAll();

	// Lưu sản phẩm
	SanPhamEntity save(SanPhamDTO dto, HttpServletRequest request);

	// Update sản phẩm
	SanPhamEntity update(SanPhamDTO dto, HttpServletRequest request);

	// Xóa sản phẩm
	void delete(long[] ids);

	// Lấy sản phẩm theo tên
	SanPhamEntity findByName(String name);
	
	//Update trạng thái;
	SanPhamEntity updateStatus(long id);
	
	//Thêm mã giảm giá 
	DiscountDTO saveDiscount(DiscountDTO dto);
	
	//Update mã giảm giá
	UpdateDiscountDTO updateDiscount(UpdateDiscountDTO dto);
	
	//Xóa mã giảm giá
	void deleteDiscount(long saleProductId);
}
