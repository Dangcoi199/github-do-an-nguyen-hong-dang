package com.nguyenhongdang.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.service.admin.IOrderInfoService;

@Controller
public class OrderController {
	@Autowired
	private IOrderInfoService orderService;

	@GetMapping("/admin_orderDetail")
	public String showOrderDetail(Model model, @RequestParam long id) {
		model.addAttribute("orderInfo", orderService.getOne(id));
		model.addAttribute("products", orderService.getProductById(orderService.getOne(id)));
		return "admin/hoadonban/order_info_detail";
	}
	@GetMapping("/admin_listOrderInfo")
	public String listOrderInfo(Model model) {
		model.addAttribute("orderInfos", orderService.findAll());
		return "admin/hoadonban/list_order_info";
	}
}
