package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {

	private final String securityKey = "shfkjdshfuewkfkwefewkjhfkw";
	private final Long expiredTime = 1000*60L*60L*24L;
	
	public String createToken(UserDTO userDTO) {
		Date now = new Date();
		return Jwts.builder()
				.setSubject(userDTO.getId())
				.setHeader(createHeader())
				.setClaims(createClaims(userDTO))
				.setExpiration(new Date(now.getTime()+expiredTime))
				.signWith(SignatureAlgorithm.HS256, securityKey)
				.compact();
	}
	
	private Map<String,Object> createHeader(){
		Map<String,Object> header= new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		return header;
	}
	
	private Map<String,Object> createClaims(UserDTO userDTO){
		Map<String,Object> claims = new HashMap<>();
		claims.put("id", userDTO.getId());
		claims.put("password", userDTO.getPassword());
		return claims;
	}
	
}
