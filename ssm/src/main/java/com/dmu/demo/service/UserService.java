package com.dmu.demo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmu.demo.dao.UserDao;
import com.dmu.demo.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 通过校验的registerform，其属性会装入user对象，
	 * 经由dao接口插入user表
	 */
	public boolean createUser(User user){
		int result = userDao.insert(user);
		//数据添加失败时，抛出异常，暂未做异常处理
		if (result==0) {
			System.out.println("添加失败");
			return false;
		}else {
			return true;
		}
		
	}
	
//	public Boolean matchUserName(String userName){
//		String string=userDao.selectByUserName(userName);
//		if (Objects.nonNull(string)) {
//			return true;
//		}else {
//			return false;
//		}
//	}
	
	//在数据库校验用户名和密码
	public User matchUser(String userName, String password){
		User user=userDao.selectByUserNameAndPassword(userName, password);
		return user;
	}
	
	//根据用户名查找用户信息
	public User selectUser(String userName){
		User user = userDao.selectUserByUserName(userName);
		return user;
	}
	
	//更新用户数据
	public int alterUser(User user){
		int result = userDao.updateByUser(user);
		return result;
	}
	
	//根据用户名删除用户数据
	public int dropUser(String userName){
		int result = userDao.deleteByUserName(userName);
		return result;
	}
}
