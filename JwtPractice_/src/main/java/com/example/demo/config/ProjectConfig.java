package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

	private final JwtAuthenticationFilter jwtFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
	    return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		
		http.authorizeHttpRequests()
		.requestMatchers("/auth/**")
		.permitAll()
		.anyRequest()
		.authenticated();
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.httpBasic().disable();
		http.formLogin().disable();
		
		return http.build();
	}
	
}
