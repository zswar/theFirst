package com.dmu.demo.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmu.demo.entity.Item;
import com.dmu.demo.service.ItemService;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 商品的增删改查
 * */

@Controller
public class MallController {
	
	@Autowired
	ItemService itemService;
	
	//查
	@RequestMapping("/mall/selectAll")
	@ResponseBody
	public JSONArray selectAll(){
		List<Item> selectAll = itemService.selectAll();
		JSONArray jsonArray = JSONArray.fromObject(selectAll);
		System.out.println(jsonArray);
		return jsonArray;
	}
	
	//增
	@RequestMapping("/mall/insert")
	@ResponseBody
	public int insert(String items){
		System.out.println(items);
		JSONArray jsonArray=JSONArray.fromObject(items);
		Item item=null;
		List<Item> list=new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			System.out.println(jsonObject.toString());
			
			item=new Item(null
						, (String)jsonObject.get("name")
						, Double.parseDouble((String) jsonObject.get("price"))
						, Integer.valueOf(jsonObject.get("stock").toString())
						, (String)jsonObject.get("img"));
			list.add(item);
			
		}
		int result = itemService.insertItems(list);
		return result;
	}
	
	//改
	@RequestMapping("/mall/update")
	@ResponseBody
	public int update(String items){
		System.out.println(items);
		JSONArray jsonArray=JSONArray.fromObject(items);
		Item item=null;
		List<Item> list=new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			item=(Item)JSONObject.toBean(jsonObject, Item.class);
			list.add(item);
		}
		int result = itemService.updateItem(list);
		return result;
	}
	
	//删
	@RequestMapping("/mall/delete")
	@ResponseBody
	public int delete(String names){
		System.out.println(names);

		List<String> list=new ArrayList<>();
		JSONArray jsonArray=JSONArray.fromObject(names);
		String name=null;
		for (int i = 0; i < jsonArray.size(); i++) {
			System.out.println(jsonArray.get(i));
			name=(String) jsonArray.get(i);
			list.add(name);
		}
		System.out.println(list);
		int result=itemService.delete(list);
		return result;
	}
	
	
	
	
	
}
