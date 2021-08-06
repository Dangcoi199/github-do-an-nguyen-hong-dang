package com.nguyenhongdang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nguyenhongdang.dto.MenuDTO;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.service.web.IMenuService;
import com.nguyenhongdang.service.web.IShoppingCartService;
import com.nguyenhongdang.service.web.IUserService;

@Component
public class MenuHandleInterceptor implements HandlerInterceptor {
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IShoppingCartService cart;
	@Autowired
	private IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		MenuDTO menu = menuService.loadMenu();
		request.setAttribute("menu", menu);
		request.setAttribute("cart_size", cart.getCount());

		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() != "anonymousUser") {
			String userName = authentication.getName();
			UserEntity entity = userService.getByUserName(userName);
			String fullName = entity.getFullname();
			if (fullName == null || fullName.isEmpty()) {
				request.setAttribute("fullNameAccount", authentication.getName());
			} else {
				request.setAttribute("fullNameAccount", fullName);
			}

		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}
}
