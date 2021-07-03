package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.UniqueBrand;

import lombok.Data;

@Data
public class BrandDTO extends AbstractDTO<BrandDTO>{
	 @UniqueBrand
	 private String brandName;
	 private String code;
	 private String image;
	 private String bytes;
	 private String shortDescription;
	 private String content;
}
