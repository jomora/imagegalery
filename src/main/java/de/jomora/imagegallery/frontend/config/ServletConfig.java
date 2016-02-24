package de.jomora.imagegallery.frontend.config;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ServletConfig {
//
//	@Bean
//	public DispatcherServlet dispatcherServlet(){
//		return new DispatcherServlet();
//	}
//	
//	@Bean
//	public ServletRegistrationBean dispatcherServletRegistration(){
//		ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet(), "/mygallery/*");
//		bean.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
//		return bean;
//	}
//	
//	@Bean
//	public WebApplicationInitializer galleryWebAppInitializeer(){
//		return new GalleryWebApplicationInitializer();
//	}
}
