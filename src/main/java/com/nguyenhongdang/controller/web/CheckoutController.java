package com.nguyenhongdang.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.service.web.IShoppingCartService;


@Controller
public class CheckoutController {
	@Autowired
	private IShoppingCartService cartService;
	@GetMapping("/showcart")
	public String showcart(Model model) {
		model.addAttribute("total", cartService.getAmount());
		model.addAttribute("cartItems", cartService.getAll());
		model.addAttribute("size", cartService.getCount());
		return "checkout";
	}
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", cartService.getAmount());
		model.addAttribute("cartItems", cartService.getAll());
		model.addAttribute("size", cartService.getCount());
		return "checkout";
	}
	@GetMapping("/buynow")
	public String buynow(@RequestParam(name = "id") int id) {
		cartService.add(id);
		return "redirect:/showcart";
	}
	@GetMapping("/clear_cart")
	public String ClearCart() {
		cartService.clear();
		return "redirect:/";
	}
	@GetMapping("/remove_item")
	public String RemoveItem(@RequestParam(name = "id") int id) {
		cartService.remove(id);
		return "redirect:/showcart";
	}
	@GetMapping("/order_success")
	public String success() {
		return "order_success";
	}
}
