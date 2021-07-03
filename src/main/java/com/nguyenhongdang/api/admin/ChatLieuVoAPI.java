package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.ChatLieuVoDTO;
import com.nguyenhongdang.service.admin.IChatLieuVoService;

@RestController
public class ChatLieuVoAPI {
	@Autowired
	private IChatLieuVoService chatLieuVoService;

	@PostMapping("/chatLieuVos")
	public ChatLieuVoDTO add(@Valid @RequestBody ChatLieuVoDTO dto) {
		return chatLieuVoService.save(dto);
	}

	@PutMapping("/chatLieuVos")
	public ChatLieuVoDTO update(@RequestBody ChatLieuVoDTO dto) {
		return chatLieuVoService.udpate(dto);
	}

	@DeleteMapping("/chatLieuVos")
	public void delete(@RequestBody long[] ids) {
		chatLieuVoService.delete(ids);
	}
}
