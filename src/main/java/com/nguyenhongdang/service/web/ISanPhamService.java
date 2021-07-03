package com.nguyenhongdang.service.web;

import java.util.List;

import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.entity.SanPhamEntity;

public interface ISanPhamService {
	// Lấy sản phẩm theo Id
	SanPhamEntity getOne(long id);

	// Lấy toàn bộ sản phẩm và phân trang
	List<ProductTransferDTO> findAll(int offset, int limit);

	// Lấy tổng số sản phẩm
	Integer getTotalItem();

	// Lấy sản phảm theo thương hiệu và phân trang
	List<ProductTransferDTO> findByBrand(String code, String gender, int offset, int limit, String loaiDayCode,
			String khoangGiaCode, String loaiDHCode);

	// Lấy tổng số sản phẩm theo thương hiệu
	Integer getTotalItemByBrand(String code, String gender, String loaiDayCode, String khoangGiaCode,
			String loaiDHCode);

	// Lấy tổng số sản phẩm theo loại sản phẩm
	Integer getTotalItemByProductType(String code, String gender, String loaiDayCode, String khoangGiaCode,
			String brandCode);

	// Lấy sản phảm theo loại sản phẩm và phân trang
	List<ProductTransferDTO> findByProductType(String code, String gender, int offset, int limit, String loaiDayCode,
			String khoangGiaCode, String brandCode);

	// Lấy tổng số sản phẩm theo khoảng giá
	Integer getTotalItemByPrice(String code, String gender, String loaiDayCode, String brandCode, String loaiDHCode);

	// Lấy sản phảm theo khoảng giá và phân trang
	List<ProductTransferDTO> findAllByPrice(String code, String gender, int offset, int limit, String loaiDayCode,
			String brandCode, String loaiDHCode);

	// Lấy tổng số sản phẩm theo giới tính
	Integer getTotalItemByGender(String gender, String brandCode, String loaiDayCode, String khoangGiaCode,
			String loaiDHCode);

	// Lấy sản phảm theo giới tính và phân trang
	List<ProductTransferDTO> findAllByGender(String gender, int offset, int limit, String brandCode, String loaiDayCode,
			String khoangGiaCode, String loaiDHCode);

	// Lấy tổng số sản phẩm tìm kiếm
	Integer getTotalItemSearch(String search);

	// Lấy sản phảm tìm kiếm và phân trang
	List<ProductTransferDTO> findByProductSearch(String search, int offset, int limit);

	// Lấy tổng số sản phẩm theo loại sản phẩm
	Integer getTotalItemByLoaiDay(String code, String gender, String loaiDHCode, String khoangGiaCode,
			String brandCode);

	// Lấy sản phảm theo loại sản phẩm và phân trang
	List<ProductTransferDTO> findByLoaiDay(String code, String gender, int offset, int limit, String loaiDHCode,
			String khoangGiaCode, String brandCode);

	// Chi tiêt sản phẩm
	ProductTransferDTO getProductDetail(String code);

	// Sản phẩm liên quan
	List<ProductTransferDTO> getSanPhamLienQuan(String code);

	// Lấy tổng số sản phẩm theo phiên bản đặc biệt
	Integer getTotalItemByProductSpecial(String loaiDayCode, String khoangGiaCode, String brandCode, String gender);

	// Lấy sản phảm phiên bản đặc biệt và phân trang
	List<ProductTransferDTO> findByProductSpecial(int offset, int limit, String loaiDayCode, String khoangGiaCode,
			String brandCode, String gender);

	// Lấy sản phảm mới nhất và phân trang
	List<ProductTransferDTO> getProductLatest(int offset, int limit, String loaiDayCode, String khoangGiaCode,
			String brandCode, String gender, String loaiDHCode);

	// Lấy top sản phẩm bán chạy
	List<ProductTransferDTO> getTopSellers(int offset, int limit);

	// Lấy tổng số sản phẩm bán chạy
	Integer getTotalItemTopSellers();
	
	//Recommend product
	List<ProductTransferDTO> getRecommend(String userName,int offset, int limit);
	
	Integer getTotalItemRecommend(String userName);
}
