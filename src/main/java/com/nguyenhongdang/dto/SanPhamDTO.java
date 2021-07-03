package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.UniqueProduct;

import lombok.Data;

@Data
public class SanPhamDTO extends AbstractDTO<SanPhamDTO> {
	@UniqueProduct
	private String name;
	private String code;
	private int gender;
	private String image;
	private String bytes;
	private int quantity;
	private long price;
	private long showDetail;
	private String content;
	private String brandCode;
	private String chatLieuMatCode;
	private String chatLieuVoCode;
	private String doChiuNuoc;
	private String duongKinhMat;
	private String kieuDongHoCode;
	private String loaiDayCode;
	private String productTypeCode;
	private String baoHanhCode;
	private String[] fileByteDetails;
	private String[] fileNameDetails;
	
	
}
