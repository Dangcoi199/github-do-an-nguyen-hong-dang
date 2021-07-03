package com.nguyenhongdang.service.admin;

import java.io.IOException;
import java.util.List;

import com.nguyenhongdang.dto.ChiTietHDNDTO;
import com.nguyenhongdang.dto.HoaDonNhapDTO;
import com.nguyenhongdang.entity.HoaDonNhapEntity;

public interface IHoaDonNhapService {
	HoaDonNhapEntity getOne(long id);

	List<HoaDonNhapEntity> findAll();

	HoaDonNhapEntity save(HoaDonNhapDTO dto) throws IOException;

	HoaDonNhapEntity update(HoaDonNhapDTO dto);

	void delete(long[] ids);
	
	HoaDonNhapEntity addChiTietHDN(ChiTietHDNDTO dto);
}
