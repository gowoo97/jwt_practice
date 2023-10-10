package com.example.demo.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.TokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final TokenProvider tokenProvider;
	

	@Override 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		String bearerToken = httpServletRequest.getHeader("Authorization");
		String jwt=null;
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			jwt=bearerToken.substring(7);
		}
		if(jwt!=null) {
			System.out.println(jwt);
			System.out.println(tokenProvider.validateToken(jwt));
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(tokenProvider.validateToken(jwt), null, new ArrayList<>()));
		}
		else {
			System.out.println("jwt is null");
		}
		
		
		
		filterChain.doFilter(request, response);
		
	}

	
	
}
