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
	<h1>缺勤记录</h1>
	<a href="init">宿管首页</a>
	<a href="student">学生查看</a>
	<a href="record">缺勤记录</a>
	<a  id="change">修改密码</a>
	<a href="logout" >退出系统</a>
	<a href="repair">报修情况</a>
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
	
	<table>
		<thead>
			<tr>
				<td>时间</td>
				<td>学号</td>
				<td>名字</td>
				<td>宿舍楼</td>
				<td>门号</td>
				<td>备注</td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${records}"  var="record">
	            <tr>
	            	<td>${record.date}</td>
	            	<td>${record.stuNo}</td>
	            	<td>${record.name}</td>
	            	<td>${sessionScope.dorm.getName() }</td>
	            	<td>${record.room}</td>
	            	<td>${record.text}</td>
	            	<td><a href="/lxh/record/delete?id=${record.id}">删除</a></td>
	            </tr>
        	</c:forEach>
		</tbody>
		
	</table>
	
	
</body>
</html>