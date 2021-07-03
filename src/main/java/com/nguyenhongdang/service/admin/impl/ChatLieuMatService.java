package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.conveter.ChatLieuMatConveter;
import com.nguyenhongdang.dto.ChatLieuMatDTO;
import com.nguyenhongdang.entity.ChatLieuMatEntity;
import com.nguyenhongdang.repository.IChatLieuMatRepository;
import com.nguyenhongdang.service.admin.IChatLieuMatService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class ChatLieuMatService implements IChatLieuMatService{

	@Autowired
	private IChatLieuMatRepository chatLieuMatRepository;
	@Autowired
	private ChatLieuMatConveter conveter;
	@Autowired
	private VNCharacterUtils Vncharacter;
	@Override
	public ChatLieuMatEntity getOne(long id) {
		ChatLieuMatEntity entity = chatLieuMatRepository.getOne(id);
		return entity;
	}

	@Override
	public List<ChatLieuMatEntity> findAll() {
		List<ChatLieuMatEntity> entities = chatLieuMatRepository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public ChatLieuMatDTO save(ChatLieuMatDTO dto) {
		ChatLieuMatEntity entity = new ChatLieuMatEntity();
		entity = conveter.toEntity(dto);
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		entity = chatLieuMatRepository.save(entity);
		return conveter.toDTO(entity);
	}

	@Override
	@Transactional
	public ChatLieuMatDTO update(ChatLieuMatDTO dto) {
		ChatLieuMatEntity entity = chatLieuMatRepository.getOne(dto.getId());
		entity = conveter.toEntity(entity, dto);
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		entity = chatLieuMatRepository.save(entity);
		return conveter.toDTO(entity);
	}

	@Override
	public void delete(long[] ids) {
		for( long id : ids) {
			chatLieuMatRepository.deleteById(id);
		}		
	}

}
