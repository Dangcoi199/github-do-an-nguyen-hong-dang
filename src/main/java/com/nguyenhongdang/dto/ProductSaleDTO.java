package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.Now;
import com.nguyenhongdang.validation.UniqueDateBegin;

import lombok.Data;

@Data
public class ProductSaleDTO  {
	@Now
	private String begin;
	@Now
	private String end;
	private String productCode;
	private String saleCode;
	 @UniqueDateBegin
	private String[] validator;
	
}
