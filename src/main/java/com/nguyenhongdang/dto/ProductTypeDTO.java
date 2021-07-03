package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.UniqueTypeName;

import lombok.Data;

@Data
public class ProductTypeDTO extends AbstractDTO<ProductTypeDTO>{
	@UniqueTypeName
	private String typeName;
	private String code;
	private String shortDescription;
}
