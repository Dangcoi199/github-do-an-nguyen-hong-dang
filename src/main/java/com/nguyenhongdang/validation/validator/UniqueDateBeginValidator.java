package com.nguyenhongdang.validation.validator;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.ProductSaleEntity;
import com.nguyenhongdang.repository.IProductSaleRepository;
import com.nguyenhongdang.utils.StringToDate;
import com.nguyenhongdang.validation.UniqueDateBegin;

public class UniqueDateBeginValidator implements ConstraintValidator<UniqueDateBegin, String[]>{
	@Autowired
	private IProductSaleRepository repo;
	@Autowired
	private StringToDate stringToDate;
	@Override
	public boolean isValid(String[] value, ConstraintValidatorContext context) {
		long productId = Long.parseLong(value[0]);
		String begin =value[1];
		Date date = stringToDate.stringtoDate(begin);
		List<ProductSaleEntity> entities = repo.findByEndAfter(date);
		if(entities.size() == 0) {
			return true;
		}else {
			for(ProductSaleEntity entity : entities) {
				if(entity.getProduct().getId() == productId) {
					return false;
				}
			}
		}
		return true;
	}
	
}
