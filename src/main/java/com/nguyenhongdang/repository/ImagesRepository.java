package com.nguyenhongdang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.ImagesEntity;
import com.nguyenhongdang.entity.SanPhamEntity;

public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {
List<ImagesEntity> findByProduct(SanPhamEntity product);
}
