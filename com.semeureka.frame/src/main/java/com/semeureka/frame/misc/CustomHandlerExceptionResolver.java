package com.semeureka.frame.misc;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class CustomHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
	public static final Logger logger = LoggerFactory
			.getLogger(CustomHandlerExceptionResolver.class);
	private Map<Class<? extends Exception>, Integer> exceptionCodeMappings;

	public void setExceptionCodeMappings(
			Map<Class<? extends Exception>, Integer> exceptionCodeMappings) {
		this.exceptionCodeMappings = exceptionCodeMappings;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		try {
			for (Entry<Class<? extends Exception>, Integer> entry : exceptionCodeMappings
					.entrySet()) {
				if (entry.getKey().isInstance(ex)) {
					response.sendError(entry.getValue(), ex.getMessage());
					return new ModelAndView();
				}
			}
		} catch (IOException ioException) {
			logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception",
					ioException);
		}
		return null;
	}
}
