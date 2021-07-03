package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.service.admin.IKieuDongHoService;

@RestController
public class KieuDongHoAPI {
	@Autowired
	private IKieuDongHoService kieuDHService;
	@PostMapping("/kieuDHs")
	public KieuDongHoEntity save(@Valid @RequestBody KieuDongHoEntity kieuDH) {
		return kieuDHService.save(kieuDH);
	}
	@DeleteMapping("/kieuDHs")
	public void deleteCate(@RequestBody long[] ids) {
		kieuDHService.delete(ids);
	}
	@PutMapping("/kieuDHs")
	public KieuDongHoEntity update(@RequestBody KieuDongHoEntity cate) {
		return kieuDHService.update(cate);
	}
}
