package com.dmu.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmu.demo.entity.User;
import com.dmu.demo.service.UserService;

import net.sf.json.JSONObject;

/**
 * 只有个登录操作 
 * */

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	
	//创建用户
	@GetMapping("/register")
	@ResponseBody
	public JSONObject register(String userName 
						 , String password
						 , String department
						 , String email
						 , String tel
						 , String question
						 , String answer
						 , HttpSession session){
		System.out.println(userName);
		//如果校验失败，则返回注册页面
		User user=new User(null,userName,password,department,email,tel,question,answer);
		//添加用户
		boolean bool=userService.createUser(user);
		Map<String, String> map= new HashMap<String, String>();
		if (bool) {
			//将注册成功的用户添加到session中
			session.setAttribute("user", user);
			//传json格式这样操作，传string的话简单些
			map.put("message", "提交成功");
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject;
		} else {
			map.put("message", "提交失败");
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject;
		}
		
		
		
		
	}
	
	
	
}
