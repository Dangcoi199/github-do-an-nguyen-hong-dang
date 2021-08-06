package com.nguyenhongdang.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.http.client.ClientProtocolException;
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
import com.nguyenhongdang.entity.RoleEntity;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.RoleRepository;
import com.nguyenhongdang.repository.UserRepository;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

@Component
public class RestFB {

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
		String link = String.format(env.getProperty("facebook.link.get.token"), env.getProperty("facebook.app.id"),
				env.getProperty("facebook.app.secret"), env.getProperty("facebook.redirect.uri"), code);
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public com.restfb.types.User getUserInfo(final String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, env.getProperty("facebook.app.secret"),
				Version.LATEST);
		return facebookClient.fetchObject("me", com.restfb.types.User.class);
	}
	
	@Transactional
	public UserDetails buildUser(com.restfb.types.User user) {
		UserEntity entity = userRepo.findByUsername(user.getId());
		if(entity == null) {
			UserEntity newUser = new UserEntity();
			newUser.setUsername(user.getId());
			newUser.setPassword(passwordEncoder.encode(user.getId()));
			newUser.setFullname(user.getName());
			newUser.setStatus(1);
			newUser.setLoaiTaiKhoan(LoaiTaiKhoanConstant.FACEBOOK);
			List<RoleEntity> roles = roleRepo.findByCode("ROLE_MEMBER");
			newUser.setRoles(roles);
			entityManager.persist(newUser);
		}		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		UserDetails userDetail = new User(user.getId(), "", enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return userDetail;
	}

}