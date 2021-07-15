package com.dmu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmu.demo.service.MachineService;

@Controller
public class MachineController {
	
	@Autowired
	private MachineService machineService;
	
	@RequestMapping("/insertMachine")
	@ResponseBody
	public int insert(int index){
		int result = machineService.insert(index);
		return result;
	}
	
	@RequestMapping("/deleteMachine")
	@ResponseBody
	public int delete(int index){
		int result = machineService.delete(index);
		return result;
	}
	
	@RequestMapping("/selectMachine")
	@ResponseBody
	public List<Integer> select(){
		List<Integer> indexs=machineService.select();
		return indexs;
	}
}
