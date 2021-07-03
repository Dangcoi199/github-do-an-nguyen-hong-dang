package com.nguyenhongdang.entity;

import java.util.ArrayList;
import java.util.List;

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
import com.nguyenhongdang.validation.UniqueSupplier;

@Entity
@Table(name = "suppliers")
@EntityListeners(AuditingEntityListener.class)
public class SupplierEntity extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "name", nullable = false)
	@UniqueSupplier
    private String name;
	
	@Column(name = "code", nullable = false)
    private String code;
	
	@Column(name = "address",nullable = false)
    private String address;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "email")
    private String email;
	
	@OneToMany(mappedBy = "supplier")
	@JsonIgnore
	private List<HoaDonNhapEntity> hoadonnhaps = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
	
	
	
}
