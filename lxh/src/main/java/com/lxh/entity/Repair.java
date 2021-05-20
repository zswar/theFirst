package com.lxh.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Repair {
	
	private Integer id;
	
	private String stuName;
	
	private Integer room;
	
	private String text;
	
	private Date date;
	
	private Integer dormId;
	
}
