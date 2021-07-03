package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.TinTucEntity;
import com.nguyenhongdang.service.admin.ITinTucService;

@Controller
public class TinTucController {
	@Autowired
	private ITinTucService service;
	@GetMapping(value = "/admin_listTinTuc")
	public String showList(Model model) {
		List<TinTucEntity> tinTucs = service.findAll();
		model.addAttribute("tinTucs", tinTucs);
		return "admin/tintuc/showlist";
	}

	@GetMapping(value = "/admin_addTinTuc")
	public String add(Model model) {
		model.addAttribute("danhMucs", service.getDanhMuc());
		return "admin/tintuc/add";
	}

	@GetMapping("/admin_updateTinTuc")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		TinTucEntity tinTuc = service.getOne(id);
		model.addAttribute("tinTuc", tinTuc);
		model.addAttribute("danhMucs", service.getDanhMuc());
		return "admin/tintuc/edit";
	}
}
