package com.lxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lxh.entity.Manager;

public interface ManagerDao {
	
	@Insert("insert into manager values(default, #{name}, #{password}, #{tel},#{dormId})")
	public int insertManager(Manager manager);
	
	@Select("select name from manager where name = #{name} ")
	String selectByName(String name);
	
	@Select("select id, name, password, dorm_id dormId from manager where name=#{name} and password=#{password}")
	Manager selectByNameAndPassword(@Param("name") String name, @Param("password") String password);

	@Update("update manager set password=#{password} where id=#{id}")
	int updatePwd(Manager manager);
	
	@Select("select id, name, password, dorm_id dormId, tel from manager ")
	List<Manager> selectAll();
	
	@Delete("delete from manager where id=#{id}")
	int deleteById(Integer id);
}
