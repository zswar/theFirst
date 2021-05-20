package com.lxh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
	
	public Integer id;
	
	public Integer stuNo;
	
	public String name;
	
	public String password;
	
	public String sex;
	
	public Integer dormId;
	
	public Integer room;
	
	public String tel;
	
}
