package com.nguyenhongdang.dto;

import lombok.Data;

@Data
public class ChiTietHDNDTO extends AbstractDTO<ChiTietHDNDTO> {
	private long donGia;
	private int soLuong;
	private String productName;
	private long hoaDonNhapId;
}
