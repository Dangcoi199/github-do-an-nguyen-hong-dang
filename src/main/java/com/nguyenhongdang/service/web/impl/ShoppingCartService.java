package com.nguyenhongdang.service.web.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.nguyenhongdang.conveter.ProductEntityTransferDTO;
import com.nguyenhongdang.dto.CartItemDTO;
import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.service.admin.ISanPhamService;
import com.nguyenhongdang.service.web.IShoppingCartService;

@SessionScope
@Service
public class ShoppingCartService implements IShoppingCartService {
	@Autowired
	private ISanPhamService sanphamService;
	
	@Autowired
	private ISanPhamRepository spRepo;

	@Autowired
	private ProductEntityTransferDTO transferDto;
	Map<Integer, CartItemDTO> maps = new HashMap<>();

	@Override
	@Transactional
	public void add(int productId) {
		SanPhamEntity entity = sanphamService.getOne(productId);
		entity.setQuantity(entity.getQuantity() - 1);
		entity = spRepo.save(entity);
		ProductTransferDTO dto = transferDto.transferDTO(entity);
		CartItemDTO cartItem = maps.get(productId);
		if (cartItem == null) {
			CartItemDTO item = new CartItemDTO();
			item.setProductId((int) dto.getId());
			item.setName(dto.getName());
			item.setCode(dto.getCode());
			item.setImage(dto.getImage());
			if (dto.getPriceSale() == null) {
				item.setPrice(dto.getPrice());
			} else {
				item.setPrice(dto.getPriceSale().longValue());
				item.setTietKiem(dto.getTietKiem().longValue());
				item.setNiemYet(dto.getPrice());
			}
			item.setThanh_Tien(item.getQty() * item.getPrice());
			maps.put(item.getProductId(), item);
		}else {
			cartItem.setQty(cartItem.getQty() + 1);
			cartItem.setThanh_Tien(cartItem.getPrice() * cartItem.getQty());
		}

	}

	@Override
	@Transactional
	public void remove(int productId) {
		CartItemDTO dto = maps.get(productId);
		SanPhamEntity productEntity = spRepo.getOne((long) productId);
		productEntity.setQuantity(productEntity.getQuantity() + dto.getQty());
		spRepo.save(productEntity);
		maps.remove(productId);

	}

	@Override
	public CartItemDTO update(int productId, int qty) {
		CartItemDTO cartItem = maps.get(productId);
		cartItem.setQty(qty);
		return cartItem;
	}

	@Override
	@Transactional
	public void clear() {
		for(CartItemDTO item : maps.values()) {
			SanPhamEntity product = spRepo.getOne((long)item.getProductId());
			product.setQuantity(product.getQuantity()+item.getQty());
			spRepo.save(product);
		}
		maps.clear();
	}

	@Override
	public Collection<CartItemDTO> getAll() {
		return maps.values();
	}

	@Override
	public int getCount() {
		return maps.values().size();
	}

	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
	}

	@Override
	public void clearAll() {
		maps.clear();
	}
}
