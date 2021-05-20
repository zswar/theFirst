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
	<a href="logout" >退出系统</a>
	<h1>学生管理</h1>
	<table>
		<thead>
			<tr>
				<td>学号</td>
				<td>名字</td>
				<td>性别</td>
				<td>宿舍楼</td>
				<td>门号</td>
				<td>电话</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}"  var="student">
	            <tr>
	            	<td>${student.stuNo}</td>
	            	<td>${student.name}</td>
	            	<td>${student.sex}</td>
	            	<td>${student.getDormId() }</td>
	            	<td>${student.room}</td>
	            	<td>${student.tel}</td>
	            	<td><a href="stuDelete?id=${student.id}">删除学生</a></td>
	            </tr>
        	</c:forEach>
		</tbody>
	</table>
	
	<h1>宿管管理</h1>
	<table>
		<thead>
			<tr>
				<td>名字</td>
				<td>宿舍楼id</td>
				<td>电话</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${managers}"  var="manager">
	            <tr>
	            	<td>${manager.name}</td>
	            	<td>${manager.getDormId() }</td>
	            	<td>${manager.tel}</td>
	            	<td><a href="manaDelete?id=${manager.id}">删除宿管</a></td>
	            </tr>
        	</c:forEach>
		</tbody>
	</table>
	
	
	
</body>
</html>