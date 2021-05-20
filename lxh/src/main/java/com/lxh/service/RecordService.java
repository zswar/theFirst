package com.lxh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxh.dao.RecordDao;
import com.lxh.entity.Record;

@Service
public class RecordService {

	@Autowired
	private RecordDao recordDao;
	
	public List<Record> selectByStudentName(String name){
		List<Record> records = recordDao.selectRecordByStudentName(name);
		return records;
	}
	
	public List<Record> selectByDormId(int dormId){
		List<Record> records = recordDao.selectRecordByDormId(dormId);
		return records;
	}
	
	public List<Record> select(){
		List<Record> records = recordDao.selectAll();
		return records;
	}
	
	public int insert(Record record){
		int result = recordDao.insert(record);
		return result;
	}
	
	public int deleteById(Integer id){
		int result = recordDao.deleteById(id);
		return result;
	}
	
	public int updateTextById(String text, Integer id){
		int result =  recordDao.updateTextById(text, id);
		return result;
	}
	
}
