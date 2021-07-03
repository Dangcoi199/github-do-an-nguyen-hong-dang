package com.nguyenhongdang.api.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.TinTucDTO;
import com.nguyenhongdang.entity.TinTucEntity;
import com.nguyenhongdang.service.admin.ITinTucService;

@RestController
public class TinTucAPI {
	@Autowired
	private ITinTucService service;

	@PostMapping("/tinTucs")
	public TinTucEntity add(@RequestBody TinTucDTO dto, HttpServletRequest request) {
		return service.save(dto, request);
	}

	@PutMapping("/tinTucs")
	public TinTucEntity update(@RequestBody TinTucDTO dto, HttpServletRequest request) {
		return service.update(dto, request);
	}

	@DeleteMapping("/tinTucs")
	public void delete(@RequestBody long[] ids) {
		service.delete(ids);
	}
}
