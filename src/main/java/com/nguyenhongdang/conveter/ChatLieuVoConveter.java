package com.nguyenhongdang.conveter;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.ChatLieuVoDTO;
import com.nguyenhongdang.entity.ChatLieuVoEntity;

@Component
public class ChatLieuVoConveter {
	public ChatLieuVoDTO toDTO(ChatLieuVoEntity entity) {
		ChatLieuVoDTO dto = new ChatLieuVoDTO();
		dto.setMaterial(entity.getMaterial());
		dto.setCode(entity.getCode());
		dto.setShortDescription(entity.getShortDescription());
		return dto;
	}

	public ChatLieuVoEntity toEntity(ChatLieuVoDTO dto) {
		ChatLieuVoEntity entity = new ChatLieuVoEntity();
		entity.setMaterial(dto.getMaterial());
		entity.setCode(dto.getCode());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}

	public ChatLieuVoEntity toEntity(ChatLieuVoEntity entity, ChatLieuVoDTO dto) {
		entity.setMaterial(dto.getMaterial());
		entity.setCode(dto.getCode());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
}
