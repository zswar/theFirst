package com.dmu.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
	
	Integer id;
	
	String name;
	
	double price;
	
	Integer stock;
	
	String img;
	
}
