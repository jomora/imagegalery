package de.jomora.imagegallery.frontend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/greeting").setViewName("greeting");
		registry.addViewController("/").setViewName("greeting");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/gallery").setViewName("gallery");
		registry.addViewController("/registration").setViewName("registration");
		registry.addViewController("/imagerow").setViewName("imagerow");
	}
}
