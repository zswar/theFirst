package com.dmu.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MachineDao {
	
	@Insert("insert into machine values(default, #{index})")
	int insert(int index);
	
	@Delete("delete from machine where ind=#{index}")
	int delete(int index);
	
	@Select("select ind from machine")
	List<Integer> select();
	
}
