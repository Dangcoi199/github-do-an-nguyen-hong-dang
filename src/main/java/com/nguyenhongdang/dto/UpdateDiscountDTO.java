package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.Now;

import lombok.Data;

@Data
public class UpdateDiscountDTO {
	@Now
	private String begin;
	@Now
	private String end;
	private String saleCode;
	private long productId;
	private String productName;
	private long saleProductId;
}
