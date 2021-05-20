package com.lxh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxh.dao.RepairDao;
import com.lxh.entity.Repair;

@Service
public class RepairService {
	
	@Autowired
	private RepairDao repairDao;
	
	public int insertRepair(Repair repair){
		int result = repairDao.insertRepair(repair);
		return result;
	}
	
	public int deleteById(Integer id){
		int result = repairDao.deleteById(id);
		return result;
	}
	
	public List<Repair> selectByDormId(Integer dormId){
		List<Repair> repairs = repairDao.selectByDormId(dormId);
		return repairs;
	}
	
	
	
}
