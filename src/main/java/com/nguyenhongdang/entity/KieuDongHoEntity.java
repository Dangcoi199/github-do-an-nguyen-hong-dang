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
import com.nguyenhongdang.validation.UniqueKieuDongHo;

@Entity
@Table(name = "kieu_dong_ho")
@EntityListeners(AuditingEntityListener.class)
public class KieuDongHoEntity extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "name", nullable = false)
	@UniqueKieuDongHo
    private String name;
 
    @Column(name = "code", nullable = false)
    private String code;
    
    @OneToMany( mappedBy = "kieuDongHo",cascade = CascadeType.ALL)
    @JsonIgnore
   	private List<SanPhamEntity> products = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getId() {
		return id;
	}

	public List<SanPhamEntity> getProducts() {
		return products;
	}

	public void setProducts(List<SanPhamEntity> products) {
		this.products = products;
	}
	
	
    
    
}
