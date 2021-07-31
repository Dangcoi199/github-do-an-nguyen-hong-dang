package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.NotNull;
import com.nguyenhongdang.validation.Now;

import lombok.Data;

@Data
public class DiscountDTO {
	@Now
	private String begin;
	@Now
	private String end;
	private String saleCode;
	@NotNull
	private long[] ids;
}
