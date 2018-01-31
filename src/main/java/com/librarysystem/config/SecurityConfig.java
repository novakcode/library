package com.librarysystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("novak").password("nole99").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/library-home").access("hasRole('ROLE_USER')");
		
		http.authorizeRequests().and().formLogin().loginPage("/library-login")
		.usernameParameter("username").passwordParameter("password").failureUrl("/login?error")
		.loginProcessingUrl("/login-form").defaultSuccessUrl("/library-home")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/library-login")
		.and().csrf();
	}

	
	
}
