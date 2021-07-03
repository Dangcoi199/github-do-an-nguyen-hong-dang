package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.ChatLieuVoEntity;
import com.nguyenhongdang.service.admin.IChatLieuVoService;

@Controller
public class ChatLieuVoController {
	@Autowired
	private IChatLieuVoService chatLieuVoService;
	@GetMapping("/admin_listChatLieuVo")
	public String showlist(Model model) {
		List<ChatLieuVoEntity> chatLieuVos = chatLieuVoService.findAll();
		model.addAttribute("chatLieuVos", chatLieuVos);
		return "admin/chatlieuvo/showlist";
	}
	@GetMapping("/admin_addChatLieuVo")
	public String add() {
		return "admin/chatlieuvo/add";
	}
	@GetMapping("/admin_updateChatLieuVo")
	public String edit(@RequestParam(value = "id") long id , Model model) {
		ChatLieuVoEntity chatLieuVo = chatLieuVoService.getOne(id);
		model.addAttribute("chatLieuVo", chatLieuVo);
		return "admin/chatlieuvo/edit";
	}
}
