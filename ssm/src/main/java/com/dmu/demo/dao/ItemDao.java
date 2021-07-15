package com.dmu.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.dmu.demo.entity.Item;

public interface ItemDao {
	
	@Select("select * from items")
	List<Item> selectAll();
	
	@Insert("insert into items values(default, #{name}, #{price}, #{stock}, #{img}) ")
	int insert(Item item);
	
	@Update("update items set stock=stock-#{stock}"
			+ "         where name=#{name} and stock>#{stock}")
	int updateByItem(Item item);
	
	@Delete("delete from items where name=#{name}")
	int deleteByName(String name);
}
