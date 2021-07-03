package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.ChatLieuMatDTO;
import com.nguyenhongdang.service.admin.IChatLieuMatService;

@RestController
public class ChatLieuMatAPI {
	@Autowired
	private IChatLieuMatService chatLieuMatService;
	
	@PostMapping("/chatLieuMats")
	public ChatLieuMatDTO add(@Valid @RequestBody ChatLieuMatDTO dto) {
		return chatLieuMatService.save(dto);
	}
	
	@PutMapping("/chatLieuMats")
	public ChatLieuMatDTO update(@RequestBody ChatLieuMatDTO dto) {
		return chatLieuMatService.update(dto);
	}
	@DeleteMapping("/chatLieuMats")
	public void delete(@RequestBody long[] ids) {
		chatLieuMatService.delete(ids);
	}
}
