package com.lxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lxh.entity.Dorm;

public interface DormDao {
	
	@Select("select id, name, brief from dorm")
	public List<Dorm> select();
	
	@Select("select id, name, brief from dorm where name=#{name}")
	public Dorm selectByName(String name);
	
	@Select("select id, name, brief from dorm where id=#{id}")
	public Dorm selectById(Integer id);
	
	@Update("update dorm set brief=#{brief} where id=#{id}")
	int updateBriefById(@Param("brief") String brief,@Param("id") Integer id);
}
