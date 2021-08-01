package com.nguyenhongdang.conveter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.UpdateDiscountDTO;
import com.nguyenhongdang.entity.ProductSaleEntity;
import com.nguyenhongdang.entity.SanPhamEntity;

@Component
public class ProductToDiscount {
	public UpdateDiscountDTO toDiscountDto(SanPhamEntity entity) {
		Date now = new Date();
		SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
		UpdateDiscountDTO dto = new UpdateDiscountDTO();
		dto.setProductId(entity.getId());
		dto.setProductName(entity.getName());
		if(entity.getSales() !=null) {
			for (ProductSaleEntity sale : entity.getSales()) {
				if (sale.getBegin().getTime() <= now.getTime() && sale.getEnd().getTime() >= now.getTime()) {
					dto.setSaleProductId(sale.getId());
					dto.setBegin(formatter.format(sale.getBegin()));
					dto.setEnd(formatter.format(sale.getEnd()));
					dto.setSaleCode(sale.getSale().getCode());
					break;
				}
			}
		}
		return dto;
	}
}
