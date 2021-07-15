package com.dmu.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmu.demo.dao.MachineDao;

@Service
public class MachineService {

	@Autowired
	private MachineDao machineDao;
	
	public int insert(int index){
		int result = machineDao.insert(index);
		return result;
	}
	
	public int delete(int index){
		int result = machineDao.delete(index);
		return result;
	}
	
	public List<Integer> select(){
		List<Integer> indexs=machineDao.select();
		return indexs;
	}
}
