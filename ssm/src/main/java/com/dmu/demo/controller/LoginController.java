package com.dmu.demo.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmu.demo.entity.User;
import com.dmu.demo.service.UserService;

import net.sf.json.JSONObject;

/**
 * 用户信息controller
 * 业务逻辑都写在controller了，后面会写在service
 * @author 
 */

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	HttpServletRequest request;
	
	//登录
	@GetMapping("/wxlogin")
	@ResponseBody
	public JSONObject wxlogin(@RequestParam("userName") String userName
							, @RequestParam("password") String password
							, HttpSession session){
		User matchUser = userService.matchUser(userName, password);
		Map<String, String> map = new HashMap<String, String>();
		if (Objects.nonNull(matchUser)) {
			session.setAttribute("user", matchUser);
			//因为微信小程序不支持session，所以想把当前用户信息存到微信端
			//后来在微信端自定义了cookie，把用户信息存在服务端了
			//这里没改，是为了记录学习过程（才不是懒）
			map.put("message", "登录成功");
			map.put("userName", userName);
			map.put("sessionId", session.getId());
			System.out.println("登录id是"+session.getId());
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject;
				
		}else {
			map.put("message", "登录失败！用户名或密码错误");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject;
		}
	}
	
	//根据用户名查找用户信息，是分别验证用户名和密码用的，不过懒得分步验证，废弃
	//写的太早了忘了，这不是废弃的，而是找回密码对应的接口
	@PostMapping("/selectUser")
	@ResponseBody
	public JSONObject selectUser(String userName
								, HttpSession session){
		System.out.println(userName);
		User matchUser = userService.selectUser(userName);
		Map<String, String> map = new HashMap<String, String>();
		if (Objects.nonNull(matchUser)) {
			System.out.println(matchUser);
			session.setAttribute("user", matchUser);
			
			
			map.put("result", "1");
			map.put("sessionId", session.getId());
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject;
				
		}else {
			map.put("result", "0");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject;
		}
	}	
	
	
	//查询当前user
	@PostMapping("/nowUser")
	@ResponseBody
	public JSONObject nowUser(HttpSession session){
//		String id = request.getSession().getId();
//		System.out.println("id是"+id);
		Object object=session.getAttribute("user");
		User user=(User)object;
		JSONObject jsonObject=JSONObject.fromObject(user);
		return jsonObject;
	}
	
	//更新user信息
	@PostMapping("/update")
	@ResponseBody
	public JSONObject updateUser(HttpSession session,String user){
		System.out.println(user);
		//将jsonstring转化为user对象
		JSONObject jsonObject1=JSONObject.fromObject(user);
		User user2=(User)JSONObject.toBean(jsonObject1, User.class);
		
		int alterUser = userService.alterUser(user2);
		if (alterUser==1) {
			session.setAttribute("user", user2);
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("result", alterUser);
		JSONObject jsonObject=JSONObject.fromObject(map);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	//注销用户
	@PostMapping("/drop")
	@ResponseBody
	public JSONObject drop(HttpSession session){
		Object object = session.getAttribute("user");
		User user=(User)object;
		int result = userService.dropUser(user.getUserName());
		if (result==1) {
			session.removeAttribute("user");
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		JSONObject jsonObject=JSONObject.fromObject(map);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	//退出登录（清除session数据）
	@PostMapping("/logout")
	@ResponseBody
	public JSONObject logout(HttpSession session){
		session.removeAttribute("user");
		int result=1;
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		JSONObject jsonObject=JSONObject.fromObject(map);
		System.out.println(jsonObject);
		return jsonObject;
		
	}
	
	
	
	
	
	
	
	
	
}
