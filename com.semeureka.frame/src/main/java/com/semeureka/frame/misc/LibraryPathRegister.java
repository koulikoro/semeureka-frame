package com.semeureka.frame.misc;

import java.lang.reflect.Field;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class LibraryPathRegister implements ServletContextAware {
	public static final Logger logger = LoggerFactory.getLogger(LibraryPathRegister.class);
	private String libraryPath = "/WEB-INF/file";
	private boolean success;

	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public boolean isSuccess() {
		return success;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		register(servletContext);
	}

	public void register(ServletContext servletContext) {
		String reallibraryPath = servletContext.getRealPath(libraryPath);
		try {
			Field field = ClassLoader.class.getDeclaredField("usr_paths");
			field.setAccessible(true);
			String[] paths = (String[]) field.get(null);
			if (!ArrayUtils.contains(paths, reallibraryPath)) {
				field.set(null, ArrayUtils.add(paths, reallibraryPath));
			}
			success = true;
		} catch (Exception e) {
			logger.error("Failed to register library path: {}", reallibraryPath, e);
			success = false;
		}
	}
}
