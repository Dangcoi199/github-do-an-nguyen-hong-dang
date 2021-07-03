package com.nguyenhongdang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.entity.ProductTypeEntity;
import com.nguyenhongdang.entity.SanPhamEntity;

public interface ISanPhamRepository extends JpaRepository<SanPhamEntity, Long> {
	SanPhamEntity findByName(String name);
	SanPhamEntity findByCode(String code);
	Integer countByBrand(BrandEntity brand);
	Integer countByProductType(ProductTypeEntity entity);
	@Query(value = "SELECT * FROM product WHERE MATCH(name,content,gender) AGAINST(?1) AND status=1 LIMIT ?2,?3",nativeQuery = true)
	List<SanPhamEntity> findBySearch(String search,Integer offset , Integer limit);
	@Query(value = "SELECT COUNT(*) FROM product WHERE MATCH(name,content,gender) AGAINST(?1) AND status=1",nativeQuery = true)
	Integer getTotalItemBySearch(String search);
}
