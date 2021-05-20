package com.lxh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxh.service.ManagerService;
import com.lxh.service.StudentService;

@Controller
public class MasterController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ManagerService managerService;
	
	
	@RequestMapping("/master/init")
	public ModelAndView init(ModelAndView modelAndView){
		modelAndView.setViewName("masterHome");
		modelAndView.addObject("students", studentService.selectAll())
					.addObject("managers", managerService.selectAll());
		return modelAndView;
	}
	
	@RequestMapping("/master/stuDelete")
	public String stuDelete(Integer id){
		studentService.deleteById(id);
		return "redirect:/master/init";
	}
	
	@RequestMapping("/master/manaDelete")
	public String manaDelete(Integer id){
		managerService.deleteById(id);
		return "redirect:/master/init";
	}
	
	@RequestMapping("/master/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/login";		
	}
	
}
