package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.service.admin.ILoaiDayService;

@Controller
public class LoaiDayController {
	@Autowired
	private ILoaiDayService loaiDayService;

	@GetMapping(value = "/admin_listLoaiDay")
	public String showCategory(Model model) {
		List<LoaiDayEntity> loaiDays = loaiDayService.findAll();
		model.addAttribute("loaiDays", loaiDays);
		return "admin/loaiday/showlist";
	}

	@GetMapping(value = "/admin_addLoaiDay")
	public String addUser() {
		return "admin/loaiday/add";
	}

	@GetMapping("/admin_updateLoaiDay")
	public String editUser(@RequestParam(value = "id") long id, Model model) {
		LoaiDayEntity loaiDay = loaiDayService.getOne(id);
		model.addAttribute("loaiDay", loaiDay);
		return "admin/loaiday/edit";
	}
}
