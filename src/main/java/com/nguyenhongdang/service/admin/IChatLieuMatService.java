package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.dto.ChatLieuMatDTO;
import com.nguyenhongdang.entity.ChatLieuMatEntity;

public interface IChatLieuMatService {
	ChatLieuMatEntity getOne(long id);
	List<ChatLieuMatEntity> findAll();
	ChatLieuMatDTO save(ChatLieuMatDTO dto);
	ChatLieuMatDTO update(ChatLieuMatDTO dto);
	void delete(long[] ids);

}
