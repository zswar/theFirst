package com.lxh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxh.entity.Dorm;
import com.lxh.entity.Manager;
import com.lxh.entity.Record;
import com.lxh.entity.Repair;
import com.lxh.entity.Student;
import com.lxh.service.DormService;
import com.lxh.service.ManagerService;
import com.lxh.service.RecordService;
import com.lxh.service.RepairService;
import com.lxh.service.StudentService;

@Controller
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private DormService dormService;
	
	@Autowired
	private RepairService repairService;
	
	@RequestMapping("/manager/init")
	public String init(){
		return "managerHome";
	}
	
	@RequestMapping("/manager/record")
	public ModelAndView managerRecord(HttpSession session, ModelAndView modelAndView){
		Manager manager = (Manager)session.getAttribute("user");
		List<Record> records = recordService.selectByDormId(manager.getDormId());
		modelAndView.setViewName("managerRecord");
		modelAndView.addObject("records", records);
		return modelAndView;
	}
	
	@RequestMapping("/manager/repair")
	public ModelAndView managerRepair(HttpSession session, ModelAndView modelAndView){
		Manager manager = (Manager)session.getAttribute("user");
		List<Repair> repairs = repairService.selectByDormId(manager.getDormId());
		modelAndView.setViewName("managerRepair");
		modelAndView.addObject("repairs", repairs);
		return modelAndView;
	}
	
	@RequestMapping("/manager/repairDel")
	public String repairDel(Integer id){
		int result = repairService.deleteById(id);
		if (result==1) {
			return "redirect:/manager/repair";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/manager/student")
	public ModelAndView managerStudent(HttpSession session, ModelAndView modelAndView){
		Manager manager = (Manager)session.getAttribute("user");
		List<Student> students=(List<Student>) studentService.selectByDormId(manager.getDormId());
		modelAndView.setViewName("managerStudent");
		modelAndView.addObject("students", students);
		return modelAndView;
	}
	
	@RequestMapping("/manager/change")
	public String change(HttpSession session, String password){
		System.out.println(password);
		Manager manager = (Manager)session.getAttribute("user");
		manager.setPassword(password);
		int result = managerService.updatePassword(manager);
		if (result==1) {
			session.setAttribute("user", manager);
			return "managerHome";
		}else {
			return "error";
		}
	}
	
	@RequestMapping("/manager/logout")
	public String logout(HttpSession session){
		//session.invalidate（）清理服务器session
		session.invalidate();
		//改为return "redirect:/login";不报错
		return "redirect:/login";
	}
	
	@RequestMapping("/manager/notice")
	public String updateBrief(String brief, HttpSession session){
		System.out.println(brief);
		Dorm dorm = (Dorm)session.getAttribute("dorm");
		int result = dormService.updateBriefById(brief, dorm.getId());
		if (result==1) {
			dorm.setBrief(brief);
			session.setAttribute("dorm", dorm);
			return "redirect:/manager/init";
		}else {
			return "error";
		}
		
	}
	
	
}
