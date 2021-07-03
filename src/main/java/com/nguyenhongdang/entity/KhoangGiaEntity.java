package com.nguyenhongdang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.nguyenhongdang.validation.UniqueKhoangGia;

@Entity
@Table(name = "khoangGia")
@EntityListeners(AuditingEntityListener.class)
public class KhoangGiaEntity extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name = "name", nullable = false)
	@UniqueKhoangGia
    private String name;
 
    @Column(name = "code", nullable = false)
    private String code;
    
    @Column(name = "moc_dau", nullable = false)
    private long mocDau;
    
    @Column(name = "moc_cuoi", nullable = false)
    private long mocCuoi;

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

	

	public long getMocDau() {
		return mocDau;
	}

	public void setMocDau(long mocDau) {
		this.mocDau = mocDau;
	}

	public long getMocCuoi() {
		return mocCuoi;
	}

	public void setMocCuoi(long mocCuoi) {
		this.mocCuoi = mocCuoi;
	}

	public long getId() {
		return id;
	}
    
    
}
