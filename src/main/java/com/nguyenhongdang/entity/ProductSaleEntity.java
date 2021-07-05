package com.nguyenhongdang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "product_sales")
@EntityListeners(AuditingEntityListener.class)
public class ProductSaleEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "batdau", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date begin;
	
	@Column(name = "ketthuc", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id")
	private SaleEntity sale;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private SanPhamEntity product;

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	public SanPhamEntity getProduct() {
		return product;
	}

	public void setProduct(SanPhamEntity product) {
		this.product = product;
	}

	public long getId() {
		return id;
	}
	
	
}
