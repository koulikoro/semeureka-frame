package com.semeureka.frame.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	@RequestMapping("/error")
	public String handler(HttpServletRequest request, Model model) {
		model.addAttribute("code", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		model.addAttribute("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		model.addAttribute("exception", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
		return "error";
	}

	@RequestMapping("/error/{code}")
	public String handler(@PathVariable("code") Integer code, Model model) {
		model.addAttribute("code", code);
		return "error";
	}
}
