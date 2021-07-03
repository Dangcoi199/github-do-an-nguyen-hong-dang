package com.nguyenhongdang.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.OrderInfoEntity;
import com.nguyenhongdang.service.admin.IOrderInfoService;
import com.nguyenhongdang.service.web.IShoppingCartService;

@RestController
public class CartAPI {
	@Autowired
	private IOrderInfoService service;
	@Autowired
	private IShoppingCartService cartService;

	@PostMapping("/add_order")
	public OrderInfoEntity add(@RequestBody OrderInfoEntity entity) {
		return service.add(entity);
	}

	@PostMapping("/add_item")
	public int addItem(@RequestBody int id) {
		cartService.add(id);
		return 1;
	}

}
