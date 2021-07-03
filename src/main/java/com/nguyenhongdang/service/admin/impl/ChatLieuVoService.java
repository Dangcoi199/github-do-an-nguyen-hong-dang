package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.conveter.ChatLieuVoConveter;
import com.nguyenhongdang.dto.ChatLieuVoDTO;
import com.nguyenhongdang.entity.ChatLieuVoEntity;
import com.nguyenhongdang.repository.IChatLieuVoRepository;
import com.nguyenhongdang.service.admin.IChatLieuVoService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class ChatLieuVoService implements IChatLieuVoService {

	@Autowired
	private IChatLieuVoRepository chatLieuVoRepository;
	@Autowired
	private ChatLieuVoConveter conveter;
	@Autowired
	private VNCharacterUtils Vncharacter;
	
	@Override
	public ChatLieuVoEntity getOne(long id) {
		return chatLieuVoRepository.getOne(id);
	}

	@Override
	public List<ChatLieuVoEntity> findAll() {
		List<ChatLieuVoEntity> entities = chatLieuVoRepository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public ChatLieuVoDTO save(ChatLieuVoDTO dto) {
		ChatLieuVoEntity entity = conveter.toEntity(dto);
		entity.setCode(Vncharacter.removeAccent(entity.getMaterial()));
		entity = chatLieuVoRepository.save(entity);
		return conveter.toDTO(entity);
	}

	@Override
	@Transactional
	public ChatLieuVoDTO udpate(ChatLieuVoDTO dto) {
		ChatLieuVoEntity entity = chatLieuVoRepository.getOne(dto.getId());
		entity = conveter.toEntity(entity, dto);
		entity.setCode(Vncharacter.removeAccent(entity.getMaterial()));
		entity = chatLieuVoRepository.save(entity);
		return conveter.toDTO(entity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			chatLieuVoRepository.deleteById(id);
		}
		
	}

}
