package com.lxh.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxh.dao.RepairDao;
import com.lxh.entity.Record;
import com.lxh.entity.Repair;
import com.lxh.entity.Student;
import com.lxh.service.RecordService;
import com.lxh.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private RepairDao repairDao;
	
	@RequestMapping("/student/init")
	public String init(){
		return "studentHome";
	}
	
	@RequestMapping("/student/record")
	public ModelAndView studentRecord(HttpSession session, ModelAndView modelAndView){
		Student student = (Student)session.getAttribute("user");
		List<Record> records = recordService.selectByStudentName(student.getName());
		modelAndView.setViewName("studentRecord");
		modelAndView.addObject("records", records);
		return modelAndView;
	}
	
	@RequestMapping("/student/change")
	public String change(HttpSession session, String password){
		System.out.println(password);
		Student student = (Student)session.getAttribute("user");
		student.setPassword(password);
		int result = studentService.updatePassword(student);
		if (result==1) {
			session.setAttribute("user", student);
			return "studentHome";
		}else {
			return "error";
		}
	}
	
	@RequestMapping("/student/logout")
	public String logout(HttpSession session){
		//session.invalidate（）清理服务器session
		session.invalidate();
		return "redirect:/login";
//		这里不能直接return login
//		return "login";
	}
	
	@RequestMapping("/student/help")
	public String help(){
		return "repair";
	}
	
	@RequestMapping("/student/repair")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public String repair(Repair repair, HttpSession session){
		int result = repairDao.insertRepair(repair);
		if (result==1) {
			return "repair";
		}else {
			return "error";
		}
		
	}
	
}