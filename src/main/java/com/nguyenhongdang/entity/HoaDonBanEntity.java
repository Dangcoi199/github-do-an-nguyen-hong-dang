package com.nguyenhongdang.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hoadonban")
@EntityListeners(AuditingEntityListener.class)
public class HoaDonBanEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "order_info_id", nullable = false)
	private OrderInfoEntity orderInfo;
	

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
