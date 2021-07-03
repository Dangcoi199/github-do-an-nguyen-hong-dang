package com.nguyenhongdang.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nguyenhongdang.entity.BaoHanhEntity;
import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.entity.CTHoaDonNhapEntity;
import com.nguyenhongdang.entity.ChatLieuMatEntity;
import com.nguyenhongdang.entity.ChatLieuVoEntity;
import com.nguyenhongdang.entity.ImagesEntity;
import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.entity.ProductSaleEntity;
import com.nguyenhongdang.entity.ProductTypeEntity;

import lombok.Data;

@Data
public class ProductTransferDTO {
	private long id;
	private String name;
	private String code;
	private String image;
	private String gender;
	private int quantity;
	private long price;
	private BigDecimal priceSale;
	private BigDecimal tietKiem;
	private long showDetail;
	private int status;
	private String content;
	private BrandEntity brand;
	private ChatLieuMatEntity chatLieuMat;
	private ChatLieuVoEntity chatLieuVo;
	private String doChiuNuoc;
	private String duongKinhMat;
	private KieuDongHoEntity kieuDongHo;
	private LoaiDayEntity loaiDay;
	private ProductTypeEntity productType;
	private BaoHanhEntity baohanh;
	private long soSao;
	private long customers_rating;
	private List<CTHoaDonNhapEntity> chiTietHDNs = new ArrayList<>();
	private List<ImagesEntity> imageDetails = new ArrayList<>();
	private ProductSaleEntity saleProduct;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
}
