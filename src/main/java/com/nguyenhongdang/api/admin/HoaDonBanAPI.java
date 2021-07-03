package com.nguyenhongdang.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.HoaDonBanEntity;
import com.nguyenhongdang.service.admin.IHoaDonBanService;

@RestController
public class HoaDonBanAPI {
	@Autowired
	private IHoaDonBanService service;

	@PostMapping("/hoaDonBans")
	public HoaDonBanEntity add(@RequestBody long id) {
		return service.add(id);
	}
	@DeleteMapping("/hoaDonBans")
	public void cancel(@RequestBody long id) {
		service.cancel(id);
	}
}
