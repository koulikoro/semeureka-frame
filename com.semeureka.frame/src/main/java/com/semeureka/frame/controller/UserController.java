package com.semeureka.frame.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.semeureka.frame.entity.User;
import com.semeureka.frame.service.UserService;

@Controller
@SessionAttributes("_user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/user")
	public String find(Model model) {
		List<User> users = userService.find();
		model.addAttribute("users", users);
		return "user";
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String save(User user) {
		userService.save(user);
		return "redirect:/user";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isRemembered() || subject.isAuthenticated()) {
			return "redirect:/"; // 已经记住或者认证过的用户重定向到首页
		}
		return "login";
	}

	// 用户名称或者密码错误时，请求未通过shiro认证时会进入此映射
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String fail(User user) {
		System.out.println(user.getUsername());
		return "login";
	}
}
