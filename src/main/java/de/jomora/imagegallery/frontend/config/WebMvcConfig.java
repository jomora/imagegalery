package de.jomora.imagegallery.frontend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addViewController("/greeting").setViewName("greeting");
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		// registry.addViewController("/error").setViewName("error");
		registry.addViewController("/gallery").setViewName("gallery");
		registry.addViewController("/registration").setViewName("registration");
		registry.addViewController("/imagerow").setViewName("imagerow");
		registry.addViewController("/test").setViewName("test");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/gallery").maxAge(3000).allowedOrigins("http://localhost:8080").allowedMethods("PUT")
				.allowedHeaders("x-requested-with").exposedHeaders("x-requested-with");
	}
}
