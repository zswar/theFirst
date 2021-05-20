package com.lxh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxh.entity.Record;
import com.lxh.entity.Student;
import com.lxh.service.RecordService;
import com.lxh.service.StudentService;

@Controller
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/record/init")
	public String init(Integer id, HttpSession session){
		Student student = studentService.selectById(id);
		session.setAttribute("student", student);
		return "record";
	}
	
	@RequestMapping("/record/insert")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public String insert(Record record){
		int result = recordService.insert(record);
		String string=null;
		if (result==1) {
			string="record";
		} else {
			string="error";
		}
		return string;
	}
	
	@RequestMapping("/record/delete")
	public String delete(Integer id){
		int result = recordService.deleteById(id);
		if (result==1) {
			return "redirect:/manager/record";
		} else {
			return "error";
		}
		
	}
	
//	@RequestMapping("/record/change")
//	public String updateTextById(String text, Integer id){
//		int result = recordService.updateTextById(text, id);
//		if (result==1) {
//			return "redirect:/manager/record";
//		} else {
//			return "error";
//		}
//	}
	
//	@RequestMapping("/record/insert")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	public String insert(String date){
//		System.out.println(date);
////		String string=null;
////		if (result==1) {
////			string="record";
////		} else {
////			
////		}
//		return null;
//	}

}