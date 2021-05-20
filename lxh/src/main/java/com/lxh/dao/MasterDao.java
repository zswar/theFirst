package com.lxh.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lxh.entity.Master;

public interface MasterDao {
	
	@Select("select name from master where name = #{name} ")
	String selectByName(String name);
	
	@Select("select * from master where name=#{name} and password=#{password}")
	Master selectByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
