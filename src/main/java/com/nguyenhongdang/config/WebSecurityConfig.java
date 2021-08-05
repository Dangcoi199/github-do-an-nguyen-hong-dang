package com.nguyenhongdang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nguyenhongdang.security.JwtAuthenticationFilter;
import com.nguyenhongdang.service.UserDetailServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// Get AuthenticationManager bean
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService) // Cung cáp userservice cho spring security
				.passwordEncoder(passwordEncoder()); // cung cấp password encoder
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**", "/admin_**")
				.access("hasAnyRole('ROLE_DEVELOPER', 'ROLE_ADMIN')")

				.antMatchers("/listUser", "/addUser", "/updateUser", "/listBH", "/addBH", "/updateBH", "/listKhoangGia",
						"/addKhoangGia", "/updateKhoangGia", "/listSlider", "/addSlider", "/updateSlider", "/listSale",
						"/addSale", "/updateSale")
				.access("hasRole('ROLE_ADMIN')")

				// Những url không cần xác thực
				.antMatchers("/login*", "/formlogin/**", "/web/**", "/pagination/**", "/uploadmedia/**", "/uploads/**",
						"/firebase-messaging-sw.js", "/demo-send-noti", "/public/**")
				.permitAll()
				.antMatchers("/trang-chu", "/", "/productByBrand", "/productByProductType", "/productByPrice")
				.permitAll()
				.antMatchers("/historyBrand", "/searchProduct", "/productByGender", "/register", "/userMembers",
						"/productByLoaiDay", "/productDetail", "/save-token")
				.permitAll()
				.antMatchers("/showcart", "/clear_cart", "/buynow", "/remove_item", "/add_item", "/add_order",
						"/order_success", "/order_detail_email", "/product_detailById", "/tin_tuc")
				.permitAll()
				.antMatchers("/danh-muc-tin", "/tin-tuc-by-danh_muc", "/phien-ban-dac_biet", "/san-pham-moi",
						"/top-sellers", "/lien-he", "/add-lien-he")
				.permitAll().anyRequest().authenticated().and().formLogin()
				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/trang-chu").failureUrl("/login?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password").and().exceptionHandling().accessDeniedPage("/403")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/trang-chu");
		
		// Cấu hình remember me, thời gian là 1296000 giây
	    http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
	    
		// Thêm một lớp Filter kiểm tra jwt
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
