package com.lxh.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxh.dao.ManagerDao;
import com.lxh.entity.Manager;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerDao managerDao;
	
	public int insert(Manager manager){
		int result = managerDao.insertManager(manager);
		return result;
	}
	
	public Boolean matchName(String name){
		String string= managerDao.selectByName(name);
		if (Objects.nonNull(string)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Manager matchUser(String userName, String password){
		Manager user=managerDao.selectByNameAndPassword(userName, password);
		return user;
	}
	
	public int updatePassword(Manager manager){
		int result = managerDao.updatePwd(manager);
		return result;
	}
	
	public List<Manager> selectAll(){
		List<Manager> managers=managerDao.selectAll();
		return managers;
	}
	
	public int deleteById(Integer id){
		int result = managerDao.deleteById(id);
		return result;
	}
}
