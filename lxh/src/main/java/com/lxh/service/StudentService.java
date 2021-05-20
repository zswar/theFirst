package com.lxh.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxh.dao.StudentDao;
import com.lxh.entity.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	public int createStudent(Student student){
		int result = studentDao.insertStudent(student);
		return result;
	}
	
	public Boolean matchName(String name){
		String string= studentDao.selectByName(name);
		if (Objects.nonNull(string)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Student matchUser(String userName, String password){
		Student user=studentDao.selectByNameAndPassword(userName, password);
		return user;
	}
	
	public List<Student> selectByDormId(int dormId){
		List<Student> students=studentDao.selectByDormId(dormId);
		return students;
	}
	
	public List<Student> selectAll(){
		List<Student> students=studentDao.selectAll();
		return students;
	}
	
	public Student selectById(Integer id){
		Student student=studentDao.selectById(id);
		return student;
	}
	
	public int updatePassword(Student student){
		int result = studentDao.updatePwd(student);
		return result;
	}
	
	public int deleteById(Integer id){
		int result = studentDao.deleteById(id);
		return result;
	}
}
