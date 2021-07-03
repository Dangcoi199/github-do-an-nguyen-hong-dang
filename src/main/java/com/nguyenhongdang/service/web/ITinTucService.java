package com.nguyenhongdang.service.web;

import java.util.List;

import com.nguyenhongdang.entity.TinTucEntity;

public interface ITinTucService {
	List<TinTucEntity> findAll();
	List<TinTucEntity> getThreeTinTuc();
	TinTucEntity getByCode(String code);
	List<TinTucEntity> getLienQuan(String code);
	List<TinTucEntity> getDocNhieu();
	List<TinTucEntity> getMoiNhat();
	List<String> getDanhMuc();
	List<TinTucEntity> findAll(int offset , int limit);
	List<TinTucEntity> getDocNhieu(String code);
	List<TinTucEntity> getMoiNhat(String code);
	List<TinTucEntity> findAllByDanhMuc(int offset , int limit,String danhMuc);
	Integer getItemByDanhMuc(String danhMuc);
	List<TinTucEntity> getDocNhieuByDM(String danhMuc);
	List<TinTucEntity> getMoiNhatByDM(String danhMuc);
	List<TinTucEntity> getThreeTinGioiThieu();
}
