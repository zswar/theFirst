package com.lxh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.lxh.entity.Dorm;
import com.lxh.entity.Manager;
import com.lxh.entity.Student;
import com.lxh.service.DormService;
import com.lxh.service.ManagerService;
import com.lxh.service.StudentService;

@Controller
public class RegisterController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DormService dormService;
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/register")
	public String init(HttpSession session){
		List<Dorm> list = dormService.select();
		session.setAttribute("dorms", list);
		return "register";
	}
	
	@RequestMapping("/studentRegister")
	public String studentRegister(Student student, HttpSession session){
		int result = studentService.createStudent(student);
		if (result!=0) {
			session.invalidate();
			return "redirect:/login";
		}else {
			return "error";
		}
	}
	
	@RequestMapping("/managerRegister")
	public String managerRegister(Manager manager, HttpSession session){
		int result = managerService.insert(manager);
		if (result!=0) {
			session.invalidate();
			return "redirect:/login";
		}else {
			return "error";
		}
	}
	
}
