package com.nguyenhongdang.dto;

import com.nguyenhongdang.validation.UniqueChatLieuMat;

import lombok.Data;
@Data
public class ChatLieuMatDTO extends AbstractDTO<ChatLieuMatDTO> {
	@UniqueChatLieuMat
	private String name;
	private String code;
	private String shortDescription;
}
