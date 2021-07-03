package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.UniqueChatLieuVo;

import lombok.Data;

@Data
public class ChatLieuVoDTO extends AbstractDTO<ChatLieuVoDTO> {
	@UniqueChatLieuVo
	private String material;
	private String code;
	private String shortDescription;
}
