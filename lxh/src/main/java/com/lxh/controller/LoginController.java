package com.lxh.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxh.entity.Dorm;
import com.lxh.entity.Manager;
import com.lxh.entity.Master;
import com.lxh.entity.Student;
import com.lxh.service.DormService;
import com.lxh.service.ManagerService;
import com.lxh.service.MasterService;
import com.lxh.service.StudentService;

@Controller
public class LoginController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private DormService dormService;
	
	@RequestMapping("/login")
	public String init(){
		return "login";
	}
	
	@RequestMapping("/login/status")
	public String loginByStatus(String status, HttpSession session){
		List<Dorm> list = dormService.select();
		session.setAttribute("dorms", list);
		String result=null;
		if ("学生".equals(status)) {
			result="redirect:/student/init";
		} 
		if ("管理员".equals(status)) {
			result="redirect:/manager/init";
		}
		if ("系统管理".equals(status)) {
			result="redirect:/master/init";
		}
		return result;
	}
	
	@PostMapping("/login/matchName")
	@ResponseBody
	public boolean matchName(String userName,String status,HttpSession session){
		boolean b=false;
		if ("学生".equals(status)) {
			b=studentService.matchName(userName);
		} 
		if ("管理员".equals(status)) {
			b=managerService.matchName(userName);
		}
		if ("系统管理".equals(status)) {
			b=masterService.matchName(userName);
		}
		return b;
	}
	
	@PostMapping("/login/matchUser")
	@ResponseBody
	public Boolean matchUser(String userName
								, String password
								, String status
								, HttpSession session){
		boolean b=false;
		if ("学生".equals(status)) {
			Student student=studentService.matchUser(userName, password);
			if (Objects.nonNull(student)) {
				session.setAttribute("user", student);
				Dorm dorm = dormService.selectById(student.getDormId());
				session.setAttribute("dorm", dorm);
				b=true;	
			}
		} 
		if ("管理员".equals(status)) {
			Manager manager=managerService.matchUser(userName, password);
			if (Objects.nonNull(manager)) {
				session.setAttribute("user", manager);
				Dorm dorm = dormService.selectById(manager.getDormId());
				session.setAttribute("dorm", dorm);
				b=true;	
			}
		}
		if ("系统管理".equals(status)) {
			Master master=masterService.matchUser(userName, password);
			if (Objects.nonNull(master)) {
				session.setAttribute("user", master);
				b=true;	
			}
		}
		return b;
	}
	
}
