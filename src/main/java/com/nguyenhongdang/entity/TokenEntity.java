package com.nguyenhongdang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tblToken")
@Data
public class TokenEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "token", nullable = false)
	private String token;
	
	@Column(name = "product_id", nullable = false)
	private long productId;
	
	
}
