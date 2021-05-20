package com.lxh.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxh.dao.MasterDao;
import com.lxh.entity.Master;

@Service
public class MasterService {
	
	@Autowired
	private MasterDao masterDao;
	
	public Boolean matchName(String name){
		String string= masterDao.selectByName(name);
		if (Objects.nonNull(string)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Master matchUser(String userName, String password){
		Master user=masterDao.selectByNameAndPassword(userName, password);
		return user;
	}
	
}
