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
	
	${sessionScope.dorm.getName() }
	
	
	<form action="insert" method="post" >
		<h1>添加缺勤记录</h1>
		<label>开始时间</label>
		<input type="date" name="date" />
		<br />
		<label>学号：</label>
		<input type="text" name="stuNo" value="${sessionScope.student.getStuNo() }" readonly="readonly"/>
		<br/>
		<label>姓名:</label>
		<input type="text" name="name" value="${sessionScope.student.getName() }" readonly="readonly"/>
		<br/>
		<label>宿舍楼</label>
		<input type="text"  value="${sessionScope.dorm.getName() }" readonly="readonly"/>
		<input type="hidden" name="dormId" value="${sessionScope.dorm.getId() }">
		<br/>
		<label>门号</label>
		<input type="text" name="room" value="${sessionScope.student.getRoom() }" readonly="readonly"/>
		<br/>
		
		<label>text：</label>
		<input type="text" name="text"/>
		<br/>
		<button>提交</button>
		<br/>
	</form>
	
	
	
	
</body>
</html>