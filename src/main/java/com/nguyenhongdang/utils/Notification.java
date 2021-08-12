package com.nguyenhongdang.utils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.entity.TokenEntity;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.repository.ITokenRepository;
@Component
public class Notification {
	@Autowired
	private Environment env;
	
	@Autowired
	private ITokenRepository tokenRepo;
	@Autowired
	private ISanPhamRepository sanPhamRepo;
	
	public void sendNotification(List<String> tokens,String productCode)  {
		/* String link = "http://localhost:8080/productDetail?code="+productCode; */
		String link= env.getProperty("app.firebase.redirect.uri")+productCode;
		System.out.println(link);
		System.out.println("Đã gửi đến :" + tokens.size());
		MulticastMessage message = MulticastMessage.builder().putData("title", "Luxury Watches")
				.putData("content", "Sản phẩm đã có hàng, quý khách hãy tham khảo !")
				.putData("link", link)
				.addAllTokens(tokens)
				.build();
		try {
			FirebaseMessaging.getInstance().sendMulticast(message);
		} catch (FirebaseMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional
	public void sendNoti(List<Long> ids) {
		for(long id : ids) {
			List<String> tokens = new ArrayList<>();
			SanPhamEntity sanPham = sanPhamRepo.getOne(id);
			List<TokenEntity> tokenEntities = tokenRepo.findByProductId(id);
			for(TokenEntity tokenEntity : tokenEntities) {
				tokens.add(tokenEntity.getToken());
			}
			if(tokens.size() != 0) {
				sendNotification(tokens, sanPham.getCode());
				tokenRepo.deleteAll(tokenEntities);
			}
		}
	}
	public void sendNotificationAdd(List<TokenEntity> tokenEntities,String productCode)  {
		List<String> tokens = new ArrayList<>();
		for(TokenEntity tokenEntity : tokenEntities) {
			tokens.add(tokenEntity.getToken());
		}
		sendNotification(tokens, productCode);
		tokenRepo.deleteAll(tokenEntities);
	}
}
