package com.nguyenhongdang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "product_id", nullable = false)
	private long productId;
	
	@Column(name = "price", nullable = false)
	private long price;
	
	@Column(name = "so_luong", nullable = false)
	private Integer soLuong;
	
	@Column(name = "thanh_tien", nullable = false)
	private long thanhTien;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_info_id")
	private OrderInfoEntity orderInfo;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public long getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(long thanhTien) {
		this.thanhTien = thanhTien;
	}

	public OrderInfoEntity getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfoEntity orderInfo) {
		this.orderInfo = orderInfo;
	}

	public long getId() {
		return id;
	}
	
	
	
}
