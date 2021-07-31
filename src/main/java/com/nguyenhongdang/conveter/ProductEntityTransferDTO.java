package com.nguyenhongdang.conveter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.entity.DanhGiaEntity;
import com.nguyenhongdang.entity.ProductSaleEntity;
import com.nguyenhongdang.entity.SanPhamEntity;

@Component
public class ProductEntityTransferDTO {
	public List<ProductTransferDTO> transferDTO(List<SanPhamEntity> entities) {
		Date now = new Date();
		List<ProductTransferDTO> products = new ArrayList<>();
		for (SanPhamEntity entity : entities) {
			ProductTransferDTO dto = new ProductTransferDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCode(entity.getCode());
			dto.setImage(entity.getImage());
			dto.setGender(entity.getGender());
			dto.setQuantity(entity.getQuantity());
			dto.setPrice(entity.getPrice());
			dto.setShowDetail(entity.getShowDetail());
			dto.setStatus(entity.getStatus());
			dto.setContent(entity.getContent());
			dto.setBrand(entity.getBrand());
			dto.setChatLieuMat(entity.getChatLieuMat());
			dto.setChatLieuVo(entity.getChatLieuVo());
			dto.setDoChiuNuoc(entity.getDoChiuNuoc());
			dto.setDuongKinhMat(entity.getDuongKinhMat());
			dto.setKieuDongHo(entity.getKieuDongHo());
			dto.setLoaiDay(entity.getLoaiDay());
			dto.setProductType(entity.getProductType());
			dto.setBaohanh(entity.getBaohanh());
			dto.setChiTietHDNs(entity.getChiTietHDNs());
			dto.setImageDetails(entity.getImageDetails());
			// Kiểm tra chương trình giảm giá có còn hạn sử dụng không
			if (entity.getSales() == null) {
				dto.setPrice(entity.getPrice());
			} else {
				for (ProductSaleEntity sale : entity.getSales()) {
					if (sale.getBegin().getTime() <= now.getTime() && sale.getEnd().getTime() >= now.getTime()) {
						dto.setSaleProduct(sale);
						BigDecimal price1 = new BigDecimal(entity.getPrice() * sale.getSale().getPercent());
						BigDecimal price2 = new BigDecimal("100");
						BigDecimal price3 = price1.divide(price2);
						BigDecimal price4 = new BigDecimal(entity.getPrice());
						BigDecimal priceSale = price4.subtract(price3);
						BigDecimal tietKiem = price4.subtract(priceSale);
						dto.setPriceSale(priceSale);
						dto.setTietKiem(tietKiem);
						break;
					}
				}
			}
			dto.setCreatedBy(entity.getCreatedBy());
			dto.setCreatedDate(entity.getCreatedDate());
			dto.setLastModifiedBy(entity.getLastModifiedBy());
			dto.setLastModifiedDate(entity.getLastModifiedDate());
			products.add(dto);
		}
		return products;
	}

	public ProductTransferDTO transferDTO(SanPhamEntity entity) {
		Date now = new Date();
		ProductTransferDTO dto = new ProductTransferDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setImage(entity.getImage());
		dto.setGender(entity.getGender());
		dto.setQuantity(entity.getQuantity());
		dto.setPrice(entity.getPrice());
		dto.setShowDetail(entity.getShowDetail());
		dto.setStatus(entity.getStatus());
		dto.setContent(entity.getContent());
		dto.setBrand(entity.getBrand());
		dto.setChatLieuMat(entity.getChatLieuMat());
		dto.setChatLieuVo(entity.getChatLieuVo());
		dto.setDoChiuNuoc(entity.getDoChiuNuoc());
		dto.setDuongKinhMat(entity.getDuongKinhMat());
		dto.setKieuDongHo(entity.getKieuDongHo());
		dto.setLoaiDay(entity.getLoaiDay());
		dto.setProductType(entity.getProductType());
		dto.setBaohanh(entity.getBaohanh());
		dto.setChiTietHDNs(entity.getChiTietHDNs());
		dto.setImageDetails(entity.getImageDetails());
		//Số sao
		List<DanhGiaEntity> danhGias = entity.getDanhGias();
		dto.setCustomers_rating(danhGias.size());
		double tongSao=0;
		for(DanhGiaEntity danhGia : danhGias) {
			tongSao = tongSao + danhGia.getSoSao();
		}
		double soSao = tongSao / (double)danhGias.size();		
		dto.setSoSao(Math.round(soSao));
		// Kiểm tra chương trình giảm giá có còn hạn sử dụng không
		if (entity.getSales() == null) {
			dto.setPrice(entity.getPrice());
		} else {
			for (ProductSaleEntity sale : entity.getSales()) {
				if (sale.getBegin().getTime() <= now.getTime() && sale.getEnd().getTime() >= now.getTime()) {
					dto.setSaleProduct(sale);
					BigDecimal price1 = new BigDecimal(entity.getPrice() * sale.getSale().getPercent());
					BigDecimal price2 = new BigDecimal("100");
					BigDecimal price3 = price1.divide(price2);
					BigDecimal price4 = new BigDecimal(entity.getPrice());
					BigDecimal priceSale = price4.subtract(price3);
					BigDecimal tietKiem = price4.subtract(priceSale);
					dto.setPriceSale(priceSale);
					dto.setTietKiem(tietKiem);
					break;
				}
			}
		}
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setLastModifiedDate(entity.getLastModifiedDate());
		return dto;
	}
	public List<ProductTransferDTO> getListProductNotSale(List<SanPhamEntity> entities) {
		List<ProductTransferDTO> dtos = this.transferDTO(entities);
		List<ProductTransferDTO> products = new ArrayList<>();
		for(ProductTransferDTO dto : dtos) {
			if(dto.getSaleProduct() == null) {
				products.add(dto);
			}
		}		
		return products;
	}
}
