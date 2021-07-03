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

@Entity
@Table(name = "chat_lieu_vo")
@EntityListeners(AuditingEntityListener.class)
public class ChatLieuVoEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ten_chat_lieu", nullable = false)
	private String material;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;

	@OneToMany(mappedBy = "chatLieuVo",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SanPhamEntity> products = new ArrayList<>();

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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
