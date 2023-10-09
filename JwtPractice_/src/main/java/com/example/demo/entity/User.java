package com.example.demo.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;
	
	@Column
	private String id;

	@Column
	private String password;
	
}
