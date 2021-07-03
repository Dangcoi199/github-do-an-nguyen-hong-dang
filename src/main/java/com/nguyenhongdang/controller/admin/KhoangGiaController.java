package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.KhoangGiaEntity;
import com.nguyenhongdang.service.admin.IKhoangGiaService;


@Controller
public class KhoangGiaController {
	@Autowired
	private IKhoangGiaService khoanGiaService;
	@GetMapping("/listKhoangGia")
	public String showlist(Model model) {
		List<KhoangGiaEntity> khoangGias = khoanGiaService.findAll();
		model.addAttribute("khoangGias", khoangGias);
		return "admin/khoanggia/showlist";
	}
	@GetMapping("/addKhoangGia")
	public String add() {
		return "admin/khoanggia/add";
	}
	@GetMapping("/updateKhoangGia")
	public String edit(@RequestParam(value = "id") long id , Model model) {
		KhoangGiaEntity khoangGia = khoanGiaService.getOne(id);
		model.addAttribute("khoangGia", khoangGia);
		return "admin/khoanggia/edit";
	}
}
