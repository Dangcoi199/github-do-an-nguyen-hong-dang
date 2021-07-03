package com.nguyenhongdang.service.web;

import java.util.Collection;

import com.nguyenhongdang.dto.CartItemDTO;

public interface IShoppingCartService {
	void add(int productId);
	void remove(int productId);
	CartItemDTO update(int productId , int qty);
	void clear();
	void clearAll();
	Collection<CartItemDTO> getAll();
	int getCount();
	double getAmount();
}
