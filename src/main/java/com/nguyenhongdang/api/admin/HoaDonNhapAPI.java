package com.nguyenhongdang.api.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.ChiTietHDNDTO;
import com.nguyenhongdang.dto.HoaDonNhapDTO;
import com.nguyenhongdang.entity.HoaDonNhapEntity;
import com.nguyenhongdang.service.admin.IHoaDonNhapService;

@RestController
public class HoaDonNhapAPI {
	@Autowired
	private IHoaDonNhapService service;
	@PostMapping("/hoadonnhaps")
	public HoaDonNhapEntity add(@RequestBody HoaDonNhapDTO dto) throws IOException {
		return service.save(dto);
	}

	@PutMapping("/hoadonnhaps")
	public HoaDonNhapEntity update(@RequestBody HoaDonNhapDTO dto) throws IOException{
		return service.save(dto);
	}

	@DeleteMapping("/hoadonnhaps")
	public void delete(@RequestBody long[] ids) {
		service.delete(ids);
	}
	@PostMapping("/ct_hoadonnhaps")
	public HoaDonNhapEntity addChiTiet(@RequestBody ChiTietHDNDTO dto){
		return service.addChiTietHDN(dto);
	}
}
