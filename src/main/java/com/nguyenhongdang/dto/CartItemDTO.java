package com.nguyenhongdang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
	private Integer productId;
	private String name;
	private String code;
	private String image;
	private Integer qty = 1;
	private long price;
	private long tietKiem;
	private long niemYet;
	private long thanh_Tien;
}
