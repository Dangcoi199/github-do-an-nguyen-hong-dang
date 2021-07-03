package com.nguyenhongdang.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hoadonnhap")
@EntityListeners(AuditingEntityListener.class)
public class HoaDonNhapEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "tongtien", nullable = false)
	private long tongTien;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplier;
	
	@OneToMany(mappedBy = "hoadonnhap" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CTHoaDonNhapEntity> chitietHDNs = new ArrayList<>();

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}

	public SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}

	public long getId() {
		return id;
	}

	public List<CTHoaDonNhapEntity> getChitietHDNs() {
		return chitietHDNs;
	}

	public void setChitietHDNs(List<CTHoaDonNhapEntity> chitietHDNs) {
		this.chitietHDNs = chitietHDNs;
	}
	
	
}
