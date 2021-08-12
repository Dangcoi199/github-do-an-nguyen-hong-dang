package com.nguyenhongdang.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenhongdang.constant.LoaiTaiKhoanConstant;
import com.nguyenhongdang.dto.GooglePojo;
import com.nguyenhongdang.entity.RoleEntity;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.RoleRepository;
import com.nguyenhongdang.repository.UserRepository;

@Component
public class GoogleUtils {
	@Autowired
	private Environment env;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired RoleRepository roleRepo;
	
	public String getToken(final String code) throws ClientProtocolException, IOException {
	    String link = env.getProperty("google.link.get.token");
	    String response = Request.Post(link)
	        .bodyForm(Form.form().add("client_id", env.getProperty("google.app.id"))
	            .add("client_secret", env.getProperty("google.app.secret"))
	            .add("redirect_uri", env.getProperty("google.redirect.uri")).add("code", code)
	            .add("grant_type", "authorization_code").build())
	        .execute().returnContent().asString();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode node = mapper.readTree(response).get("access_token");
	    return node.textValue();
	  }

	
	public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
	    String link = env.getProperty("google.link.get.user_info") + accessToken;
	    String response = Request.Get(link).execute().returnContent().asString();
	    ObjectMapper mapper = new ObjectMapper();
	    GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
	    System.out.println(googlePojo);
	    return googlePojo;
	  }

	@Transactional
	public UserDetails buildUser(GooglePojo googlePojo) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<>();
		UserEntity entity = userRepo.findByUsername(googlePojo.getEmail());
		if(entity == null) {
			UserEntity newUser = new UserEntity();
			newUser.setUsername(googlePojo.getEmail());
			newUser.setPassword(passwordEncoder.encode(googlePojo.getId()));
			newUser.setEmail(googlePojo.getEmail());
			newUser.setStatus(1);
			newUser.setLoaiTaiKhoan(LoaiTaiKhoanConstant.GOOGLE);
			List<RoleEntity> roles = roleRepo.findByCode("ROLE_MEMBER");
			newUser.setRoles(roles);
			entityManager.persist(newUser);			
			authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		}else {
			for(RoleEntity role : entity.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getCode()));
			}
		}
		UserDetails userDetails = new User(googlePojo.getEmail(), "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return userDetails;
	}
}
