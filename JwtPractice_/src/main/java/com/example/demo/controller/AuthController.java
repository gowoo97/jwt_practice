package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.TokenProvider;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/signup")
	public void signup(UserDTO user) {
		userService.enroll(user);
	}
	
	@PostMapping("/signin")
	public String signin(UserDTO user) {
		if(userService.login(user)) {
			return tokenProvider.createToken(user);
		}
		else {
			return null;
		}
	}
	
}
