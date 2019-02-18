package com.wisehub.platform.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.cache.StrongCacheStorage;
import freemarker.template.TemplateExceptionHandler;

@Configuration
public class BaseConfig {

	@Bean
	public FreeMarkerViewResolver freemarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freemarker.template.Configuration config = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
		
		config.setTemplateLoader(new SpringTemplateLoader(new DefaultResourceLoader(), "/templates"));
		config.setURLEscapingCharset("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setTemplateUpdateDelayMilliseconds(Long.MAX_VALUE);
		config.setCacheStorage(new StrongCacheStorage());

		freeMarkerConfigurer.setConfiguration(config);
		
		return freeMarkerConfigurer;
	}
}
