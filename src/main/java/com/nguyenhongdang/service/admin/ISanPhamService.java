package com.nguyenhongdang.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nguyenhongdang.dto.ProductSaleDTO;
import com.nguyenhongdang.dto.SanPhamDTO;
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

	// Lưu mã giảm giá cho sản phẩm
	ProductSaleDTO saveProductSale(ProductSaleDTO dto);

	// Lấy sản phẩm theo tên
	SanPhamEntity findByName(String name);
	
	//Update trạng thái;
	SanPhamEntity updateStatus(long id);
}
