package de.jomora.imagegallery.frontend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Be carefule with the ordering of the method calls. Call more strict
		// rules before less strict ones.
		http.authorizeRequests().antMatchers("/gallery").authenticated()
				.antMatchers("/registration").permitAll()
				.antMatchers("/bootstrap-3.3.6-dist/**", "/js/*", "/css/*").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
				.permitAll().defaultSuccessUrl("/gallery").failureUrl("/login").and().logout().permitAll().and().csrf();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select user_name,password,enabled from customer where user_name=?")
				.authoritiesByUsernameQuery("select user_name, authority from authority where user_name=?");

	}	

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		handler.setTargetUrlParameter("/gallery");
		return handler;
	}
}
