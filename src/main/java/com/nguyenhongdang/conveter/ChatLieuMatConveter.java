package com.nguyenhongdang.conveter;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.ChatLieuMatDTO;
import com.nguyenhongdang.entity.ChatLieuMatEntity;
@Component
public class ChatLieuMatConveter {
	public ChatLieuMatDTO toDTO(ChatLieuMatEntity entity) {
		ChatLieuMatDTO dto = new ChatLieuMatDTO();
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setShortDescription(entity.getShortDescription());
		return dto;
	}

	public ChatLieuMatEntity toEntity(ChatLieuMatDTO dto) {
		ChatLieuMatEntity entity = new ChatLieuMatEntity();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}

	public ChatLieuMatEntity toEntity(ChatLieuMatEntity entity, ChatLieuMatDTO dto) {
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
}
