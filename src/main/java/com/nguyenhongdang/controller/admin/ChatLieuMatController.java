package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.ChatLieuMatEntity;
import com.nguyenhongdang.service.admin.IChatLieuMatService;


@Controller
public class ChatLieuMatController {
	@Autowired
	private IChatLieuMatService chatLieuMatService;
	
	@GetMapping("/admin_listChatLieuMat")
	public String showlist(Model model) {
		List<ChatLieuMatEntity> chatLieus = chatLieuMatService.findAll();
		model.addAttribute("chatLieus", chatLieus);
		return "admin/chatlieumat/showlist";
	}
	@GetMapping("/admin_addChatLieuMat")
	public String add() {
		return "admin/chatlieumat/add";
	}
	@GetMapping("/admin_updateChatLieuMat")
	public String edit(@RequestParam(value = "id") long id , Model model) {
		ChatLieuMatEntity chatLieu = chatLieuMatService.getOne(id);
		model.addAttribute("chatLieu", chatLieu);
		return "admin/chatlieumat/edit";
	}
}
