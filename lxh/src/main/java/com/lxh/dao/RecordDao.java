package com.lxh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lxh.entity.Record;

public interface RecordDao {

	@Select("select id, date, stu_no stuNo, name, dorm_id dormId, room, text from record where name=#{name}")
	public List<Record> selectRecordByStudentName(String name);
	
	@Select("select id, date, stu_no stuNo, name, dorm_id dormId, room, text from record ")
	public List<Record> selectAll();
	
	@Select("select id, date, stu_no stuNo, name, dorm_id dormId, room, text from record where dorm_id=#{dormId}")
	public List<Record> selectRecordByDormId(int dormId);
	
	@Insert("insert into record values(default, #{date}, #{stuNo}, #{name}, #{dormId}, #{room}, #{text})")
	public int insert(Record record);
	
	@Delete("delete from record where id=#{id}")
	int deleteById(Integer id);
	
	@Update("update record set text=#{text} where id=#{id}")
	int updateTextById(String text, Integer id);
}
