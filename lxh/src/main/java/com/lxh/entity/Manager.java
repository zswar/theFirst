package com.lxh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Manager {
	
	public Integer id;
	
	public String name;
	
	public String password;
	
	public Integer dormId;
	
	public String tel;
	
}
