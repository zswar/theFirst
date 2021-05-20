<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</head>
<body>
	<h1>学生首页</h1>
	<a href="init">学生首页</a>
	<a href="record">缺勤记录</a>
	<a  id="change">修改密码</a>
	<a href="logout" >退出系统</a>
	<a href="help" >报修</a>
	<form action="change" method="post" style="opacity:0" id="toChange">
		<label>请输入新密码：</label>
		<input type="password" name="password" />
		<br/>
		<button>提交</button>
	</form>
	<script >
		$("#change").click(function () {
			$("#toChange").css("opacity",1);
		})
	</script>
	
	<h2>欢迎登陆宿舍管理系统！</h2>
	<textarea rows="10" cols="30" name="brief" id="brief" readonly="readonly">${sessionScope.dorm.getBrief() }</textarea>
	
	
</body>
</html>