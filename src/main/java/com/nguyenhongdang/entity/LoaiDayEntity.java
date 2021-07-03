package com.nguyenhongdang.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nguyenhongdang.validation.UniqueLoaiDay;

@Entity
@Table(name = "loai_day")
@EntityListeners(AuditingEntityListener.class)
public class LoaiDayEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "loai_day", nullable = false)
	@UniqueLoaiDay
	private String loaiDay;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;

	@OneToMany(mappedBy = "loaiDay",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SanPhamEntity> products = new ArrayList<>();

	public String getLoaiDay() {
		return loaiDay;
	}

	public void setLoaiDay(String loaiDay) {
		this.loaiDay = loaiDay;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<SanPhamEntity> getProducts() {
		return products;
	}

	public void setProducts(List<SanPhamEntity> products) {
		this.products = products;
	}

}
