package com.lxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lxh.entity.Repair;

public interface RepairDao {
	
	@Insert("insert into repair values(default, #{stuName}, #{room}, #{text}, #{date}, #{dormId})")
	int insertRepair(Repair repair);
	
	@Select("select * from repair where dormId = #{dormId} order by room")
	List<Repair> selectByDormId(Integer dormId);
	
	@Delete("delete from repair where id = #{id}")
	int deleteById(Integer id);
	
}
