package com.nguyenhongdang.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.dto.GooglePojo;
import com.nguyenhongdang.entity.RoleEntity;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.service.admin.ILienHeService;
import com.nguyenhongdang.service.admin.IOrderInfoService;
import com.nguyenhongdang.service.admin.IRoleService;
import com.nguyenhongdang.service.admin.IUserService;
import com.nguyenhongdang.utils.GoogleUtils;

@Controller
public class AdminController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IOrderInfoService orderService;
	@Autowired
	private ILienHeService lienHeService;
	
	@Autowired
	private GoogleUtils googleUtil;
	
	@RequestMapping("/login-google")
	  public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
	    String code = request.getParameter("code");
	    
	    if (code == null || code.isEmpty()) {
	      return "redirect:/login?google=error";
	    }
	    String accessToken = googleUtil.getToken(code);
	    
	    GooglePojo googlePojo = googleUtil.getUserInfo(accessToken);
	    UserDetails userDetail = googleUtil.buildUser(googlePojo);
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
	        userDetail.getAuthorities());
	    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    return "redirect:/";
	  }
	
	@GetMapping(value = "/admin_index")
	public String index(Model model) {
		model.addAttribute("orderInfoSize",orderService.findAll().size());
		model.addAttribute("sizeLienHes",lienHeService.findAllByStatus().size());
		return "admin/index";
	}
	@GetMapping(value = "/listUser")
	public String getUser(Model model) {
		List<UserEntity> users = userService.findUserByStatus();
		model.addAttribute("users", users);
		return "admin/user/showUser";
	}
	@GetMapping(value = "/addUser")
	public String addUser(Model model) {
		List<RoleEntity> roles = roleService.findAll();
		model.addAttribute("roles",roles);
		return "admin/user/addUser";
	}
	@GetMapping("/updateUser")
	public String editUser(@RequestParam(value = "id") long id , Model model) {
		UserEntity oldUser = userService.getOne(id);
		model.addAttribute("oldUser", oldUser);
		List<RoleEntity> roles = roleService.findAll();
		model.addAttribute("roles",roles);
		List<RoleEntity> roleChooses = oldUser.getRoles();
		model.addAttribute("roleChooses", roleChooses.get(0));
		return "admin/user/editUser";
	}
}
