package com.nguyenhongdang.entity;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "chitiethdn")
@EntityListeners(AuditingEntityListener.class)
public class CTHoaDonNhapEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hoadonnhap_id")
	private HoaDonNhapEntity hoadonnhap;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private SanPhamEntity product;
	
	@Column(name = "dongia", nullable = false)
    private long donGia;
	
	@Column(name = "soluong", nullable = false)
    private int soLuong;
	
	@Column(name = "thanhtien", nullable = false)
    private long thanhTien;

	public HoaDonNhapEntity getHoadonnhap() {
		return hoadonnhap;
	}

	public void setHoadonnhap(HoaDonNhapEntity hoadonnhap) {
		this.hoadonnhap = hoadonnhap;
	}

	public SanPhamEntity getProduct() {
		return product;
	}

	public void setProduct(SanPhamEntity product) {
		this.product = product;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public long getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(long thanhTien) {
		this.thanhTien = thanhTien;
	}

	public long getId() {
		return id;
	}
	
	
}
