package com.lxh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxh.dao.DormDao;
import com.lxh.entity.Dorm;

@Service
public class DormService {

	@Autowired
	private DormDao dormDao;
	
	public List<Dorm> select(){
		List<Dorm> dorms= dormDao.select();
		return dorms;
	}
	
	public Dorm selectByName(String name){
		Dorm dorm=dormDao.selectByName(name);
		return dorm;
	}
	
	public Dorm selectById(Integer id){
		Dorm dorm=dormDao.selectById(id);
		return dorm;
	}
	
	public int updateBriefById(String brief, Integer id){
		int result=dormDao.updateBriefById(brief, id);
		return result;
	}
}
