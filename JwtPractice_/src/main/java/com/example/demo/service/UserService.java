package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

	public void enroll(UserDTO userDTO) {
		
		User user=User.builder().id(userDTO.getId()).password(userDTO.getPassword()).build();
		userRepository.save(user);
		
	}
	
	
	public boolean login(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId());
		
		if(validate(user,userDTO)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private boolean validate(User user,UserDTO userDTO) {
		if(user.getId().equals(userDTO.getId()) && user.getPassword().equals(userDTO.getPassword())) {
			return true;
		}
		else 
			return false;
	}
	
	
}
