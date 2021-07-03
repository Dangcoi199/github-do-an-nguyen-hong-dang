package com.nguyenhongdang.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.HoaDonBanEntity;

import com.nguyenhongdang.service.admin.IHoaDonBanService;
import com.nguyenhongdang.service.admin.IOrderInfoService;

@Controller
public class HoaDonBanController {
	@Autowired
	private IOrderInfoService orderService;
	@Autowired
	private IHoaDonBanService hdbService;

	@GetMapping("/admin_listHDB")
	public String showList(Model model) {
		model.addAttribute("hoaDonBans", hdbService.getAll());
		return "admin/hoadonban/showlist";
	}
	
	@GetMapping("/admin_detailHDB")
	public String showDetailOrderInfo(Model model, @RequestParam long id) {
		HoaDonBanEntity hoaDonBan = hdbService.getOne(id);
		model.addAttribute("hoaDonBan", hoaDonBan);
		model.addAttribute("products",
				orderService.getProductById(orderService.getOne(hoaDonBan.getOrderInfo().getId())));
		return "admin/hoadonban/detail";
	}
	@GetMapping("/admin_hoadonbanngay")
	public String showListHoaDonTheoNgay(Model model , @RequestParam(name = "date_hdb",defaultValue = "all") String date_hdb) {
		model.addAttribute("hoaDonBans", hdbService.findByCreateDate(date_hdb));
		return "admin/hoadonban/thongkengay";
	}
	@GetMapping("/admin_hoadonbanthang")
	public String showListHoaDonTheoThang(Model model , @RequestParam(name = "month_hdb",defaultValue = "all") String month_hdb) {
		model.addAttribute("hoaDonBans", hdbService.findByCreateDate(month_hdb));
		return "admin/hoadonban/thongkethang";
	}
	
}
