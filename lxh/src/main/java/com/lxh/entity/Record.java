package com.lxh.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Record {
	
	private Integer id;
	
	private Date date;
	
	private Integer stuNo;
	
	private String name;
	
	private Integer dormId;
	
	private Integer room;
	
	private String text;
}
