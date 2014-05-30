package com.semeureka.frame.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThemeController {
	@RequestMapping(value = "/theme", method = RequestMethod.GET)
	public String show() {
		return "theme";
	}

	@RequestMapping(value = "/theme", method = RequestMethod.POST)
	public String update(@RequestParam String theme, HttpServletResponse response) {
		Cookie cookie = new Cookie("theme", theme);
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		return "redirect:theme";
	}
}
