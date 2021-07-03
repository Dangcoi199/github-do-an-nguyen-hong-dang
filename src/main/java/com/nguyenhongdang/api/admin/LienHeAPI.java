package com.nguyenhongdang.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.SendMailDTO;
import com.nguyenhongdang.entity.LienHeEntity;
import com.nguyenhongdang.service.admin.ILienHeService;

@RestController("lienHeApiOfAdmin")
public class LienHeAPI {
	@Autowired
	private ILienHeService service;
	@PostMapping("/lienHes")
	public LienHeEntity save(@RequestBody SendMailDTO dto) {
		return service.mailing(dto);
	}
	@DeleteMapping("/lienHes")
	public void deleteCate(@RequestBody long[] ids) {
		service.delete(ids);
	}
}
