package com.dmu.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Integer id;
	
	private	String userName;
	
	private String password;
	
	private String department;
	
	private String email;
	
	private String tel;
	
	private String question;
	
	private String answer;
	
	
}
