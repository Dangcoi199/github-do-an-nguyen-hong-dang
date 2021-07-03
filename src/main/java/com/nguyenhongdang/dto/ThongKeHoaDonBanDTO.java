package com.nguyenhongdang.dto;

import java.util.ArrayList;
import java.util.List;

import com.nguyenhongdang.entity.HoaDonBanEntity;

import lombok.Data;

@Data
public class ThongKeHoaDonBanDTO {
	private List<HoaDonBanEntity> hoaDonBans = new ArrayList<>();
	private long tongTien;
}
