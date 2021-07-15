package com.dmu.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmu.demo.dao.ItemDao;
import com.dmu.demo.entity.Item;

@Service
public class ItemService {

	@Autowired
	ItemDao itemDao;
	//查找所有商品
	public List<Item> selectAll(){
		List<Item> selectAll = itemDao.selectAll();
		return selectAll;
	}
	
	//将商品插入数据库
	public int insertItems(List<Item> items){
		int result=0;
		for (Item item : items) {
			result = result+itemDao.insert(item);
		}
		return result;
	}
	
	//更新商品信息
	public int updateItem(List<Item> items){
		int result=0;
		for (Item item : items) {
			result = result+itemDao.updateByItem(item);
		}
		return result;
	}
	
	//下架商品
	public int delete(List<String> names){
		int result=0;
		for (String name : names) {
			result = result+itemDao.deleteByName(name);
		}
		return result;
	}
	
}
