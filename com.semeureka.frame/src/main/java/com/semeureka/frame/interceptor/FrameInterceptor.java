package com.semeureka.frame.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.semeureka.frame.entity.Menu;
import com.semeureka.frame.service.MenuService;

public class FrameInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(FrameInterceptor.class);
	@Autowired
	private MenuService menuService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("ctx", request.getContextPath());
		String requestURI = (String) request.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH);
		if (requestURI == null) {
			requestURI = request.getRequestURI().substring(request.getContextPath().length());
		}
		logger.trace("拦截到：{} ", requestURI);
		Menu menu = menuService.findByUrl(requestURI);
		if (menu != null) {
			Menu[] navs = new Menu[menu.getLevel() + 1];
			while (menu != null) {
				navs[menu.getLevel()] = menu;
				menu = menu.getParent();
			}
			session.setAttribute("navs", navs);
		}
	}
}
