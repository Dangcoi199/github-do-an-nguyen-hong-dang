package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.service.admin.IKieuDongHoService;

@Controller
public class KieuDongHoController {
	@Autowired
	private IKieuDongHoService kieuDongHoService;

	@GetMapping(value = "/admin_listKieuDH")
	public String showCategory(Model model) {
		List<KieuDongHoEntity> kieuDHs = kieuDongHoService.findAll();
		model.addAttribute("kieuDHs", kieuDHs);
		return "admin/kieudongho/showlist";
	}

	@GetMapping(value = "/admin_addKieuDH")
	public String addUser() {
		return "admin/kieudongho/add";
	}

	@GetMapping("/admin_updateKieuDH")
	public String editUser(@RequestParam(value = "id") long id, Model model) {
		KieuDongHoEntity kieuDH = kieuDongHoService.getOne(id);
		model.addAttribute("kieuDH", kieuDH);
		return "admin/kieudongho/edit";
	}
}
