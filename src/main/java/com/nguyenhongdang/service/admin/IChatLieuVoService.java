package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.dto.ChatLieuVoDTO;
import com.nguyenhongdang.entity.ChatLieuVoEntity;

public interface IChatLieuVoService {
	ChatLieuVoEntity getOne(long id);

	List<ChatLieuVoEntity> findAll();

	ChatLieuVoDTO save(ChatLieuVoDTO dto);

	ChatLieuVoDTO udpate(ChatLieuVoDTO dto);

	void delete(long[] ids);
}
