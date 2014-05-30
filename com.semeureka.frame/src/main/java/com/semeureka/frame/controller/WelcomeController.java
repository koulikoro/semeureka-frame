package com.semeureka.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping("/welcome")
	public String Welcome(Model model) {
		return "welcome";
	}
}
