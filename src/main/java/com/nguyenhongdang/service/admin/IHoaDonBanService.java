package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.dto.ThongKeHoaDonBanDTO;
import com.nguyenhongdang.entity.HoaDonBanEntity;

public interface IHoaDonBanService {
	HoaDonBanEntity add(long id);
	void cancel(long id);
	List<HoaDonBanEntity> getAll();
	HoaDonBanEntity getOne(long id);
	List<HoaDonBanEntity> getByUserName(String username);
	ThongKeHoaDonBanDTO findByCreateDate(String date);
}
