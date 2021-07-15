package com.dmu.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dmu.demo.entity.User;

public interface UserDao {
	/**
	 * 往user表插入user
	 * */
	@Insert("insert into user values( default"
			+ "						, #{userName}"
			+ "						, #{password}"
			+ "						, #{department}"
			+ "						, #{email}"
			+ "						, #{tel}"
			+ "						, #{question}"
			+ "						, #{answer})")
	int insert(User user);
	
	@Select("select userName from user where userName = #{userName} ")
	String selectByUserName(String userName);
	
	@Select("select		id"
			+ "		  , userName"
			+ "		  , password"
			+ "		  , department"
			+ "		  , email"
			+ "		  , tel"
			+ "		  , question"
			+ "		  , answer"
			+ "	   from user"
			+ "	  where userName = #{userName}"
			+ "		and password = #{password}")
	User selectByUserNameAndPassword(@Param("userName") String userName
			  					   , @Param("password") String password);
	
	@Select("select		id"
			+ "		  , userName"
			+ "		  , password"
			+ "		  , department"
			+ "		  , email"
			+ "		  , tel"
			+ "		  , question"
			+ "		  , answer"
			+ "	   from user"
			+ "	  where userName = #{userName}")
	User selectUserByUserName(@Param("userName") String userName);
	
	
	@Update("update user set password = #{password} "
			+ "			   , department = #{department} "
			+ "			   , email = #{email}"
			+ "	 		   , tel = #{tel}"
			+ "			   , question = #{question}"
			+ "			   , answer = #{answer}"
			+ "		   where userName = #{userName} ")
	int updateByUser(User user);
	
	@Delete("delete from user where userName = #{userName}")
	int deleteByUserName(String userName);
	
}
