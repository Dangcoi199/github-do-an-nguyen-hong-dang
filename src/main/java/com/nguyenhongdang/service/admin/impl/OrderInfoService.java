package com.nguyenhongdang.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.StatusConstant;
import com.nguyenhongdang.dto.CartItemDTO;
import com.nguyenhongdang.entity.OrderDetailEntity;
import com.nguyenhongdang.entity.OrderInfoEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.repository.OrderRepository;

import com.nguyenhongdang.service.admin.IOrderInfoService;
import com.nguyenhongdang.service.web.IShoppingCartService;

@Service
public class OrderInfoService implements IOrderInfoService{
	@Autowired
	private IShoppingCartService cart;
	@Autowired
	private OrderRepository repository;
	@Autowired
	EntityManager entityManager;
	@Autowired
	private ISanPhamRepository sanPhamRepo;
	
	@Override
	@Transactional
	public OrderInfoEntity add(OrderInfoEntity entity) {
		entity.setTrangThai(StatusConstant.ChoXacNhan);
		entity= repository.save(entity);
		for(CartItemDTO dto : cart.getAll()) {
			OrderDetailEntity orderDetail = new OrderDetailEntity();
			orderDetail.setName(dto.getName());
			orderDetail.setPrice(dto.getPrice());
			orderDetail.setSoLuong(dto.getQty());
			orderDetail.setThanhTien(dto.getThanh_Tien());
			orderDetail.setProductId(dto.getProductId());
			orderDetail.setOrderInfo(entity);
			entityManager.persist(orderDetail);
		}
		cart.clearAll();
		return entity;
	}

	@Override
	public List<OrderInfoEntity> findAll() {
		String hql = "FROM OrderInfoEntity E WHERE E.trangThai =:status ";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<OrderInfoEntity> query = session.createQuery(hql, OrderInfoEntity.class);
		query.setParameter("status", StatusConstant.ChoXacNhan);
		return query.getResultList();
	}

	@Override
	public OrderInfoEntity getOne(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<SanPhamEntity> getProductById(OrderInfoEntity orderInfo) {
		List<SanPhamEntity> products = new ArrayList<>();
		for(OrderDetailEntity orderDetail : orderInfo.getOrderDetails()) {
			SanPhamEntity entity = sanPhamRepo.getOne(orderDetail.getProductId());
			products.add(entity);
		}
		return products;
	}


}
