package com.nguyenhongdang.dto;

import lombok.Data;

@Data
public class HoaDonNhapDTO extends AbstractDTO<HoaDonNhapDTO> {
	private String supplierCode;
	 private String excelFileName;
	 private String bytes;
}
