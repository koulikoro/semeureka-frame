package com.semeureka.frame.misc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ConfigService {
	public static final Logger logger = LoggerFactory.getLogger(ConfigService.class);
	@Autowired
	private ConversionService conversionService;
	@Value("classpath:config.properties")
	private Resource configResource;
	private Properties config = new Properties();
	private boolean updated; // updated mark

	@PostConstruct
	public void load() throws IOException {
		InputStream in = configResource.getInputStream();
		config.load(in);
		in.close();
	}

	@PreDestroy
	@Scheduled(initialDelayString = "${config.save.period}", fixedDelayString = "${config.save.period}")
	public void store() throws IOException {
		if (updated) {
			updated = false;
			FileOutputStream out = new FileOutputStream(configResource.getFile());
			config.store(out, null);
			out.close();
			logger.info("Update config property file");
		}
	}

	@Around(value = "@annotation(org.springframework.beans.factory.annotation.Value) && args(p) && @annotation(a)")
	public Object aopProperty(final ProceedingJoinPoint pjp, Object p, Value a) throws Throwable {
		try {
			String aValue = a.value();
			if (aValue.matches("\\$\\{\\S+\\}")) {
				String key = aValue.substring(2, aValue.length() - 1).trim();
				String value = conversionService.convert(p, String.class);
				config.setProperty(key, value);
				updated = true;
				logger.debug("Config set property({}, {})", key, value);
			}
		} catch (Exception e) {
			logger.warn("Failed to convert {} to string", p, e);
		}
		return pjp.proceed();
	}
}
