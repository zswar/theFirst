package com.lxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lxh.entity.Student;

public interface StudentDao {
	
	@Insert("insert into student values(default, #{stuNo}"
			+ ", #{name}, #{password}, #{sex}, #{dormId}, #{room}, #{tel})")
	public int insertStudent(Student student);
	
	@Select("select name from student where name = #{name} ")
	String selectByName(String name);
	
	@Select("select id, stu_no stuNo, name, password, sex, dorm_id dormId, room, tel from "
			+ " student where name=#{name} and password=#{password}")
	Student selectByNameAndPassword(@Param("name") String name, @Param("password") String password);

	@Select("select id, stu_no stuNo, name, password, sex, dorm_id dormId, room, tel from "
			+ " student where dorm_id=#{dormId}")
	List<Student> selectByDormId(Integer dormId);
	
	@Select("select id, stu_no stuNo, name, password, sex, dorm_id dormId, room, tel from "
			+ " student ")
	List<Student> selectAll();
	
	@Select("select id, stu_no stuNo, name, password, sex, dorm_id dormId, room, tel from"
			+ " student where id=#{id} ")
	Student selectById(Integer id);
	
	@Update("update student set password=#{password} where id=#{id}")
	int updatePwd(Student student);
	
	@Delete("delete from student where id=#{id}")
	int deleteById(Integer id);
}
