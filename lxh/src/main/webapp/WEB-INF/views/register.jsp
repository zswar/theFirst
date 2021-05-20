<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<style>
	
	#student {
		opacity: 0;
	}
	
	#manager {
		opacity: 0;
	}
	
	
</style>

</head>
<body>
	
	请选择注册身份：
	<input type="radio" name="status" value="0">学生
	<input type="radio" name="status" value="1">管理员
	<button id="choose">确认</button>
	
	<form action="studentRegister" method="post" id="student">
		<h1>学生注册</h1>
		<label>学号：</label>
		<input type="text" name="stuNo" id="stuNo"/>
		<br/>
		<label>姓名:</label>
		<input type="text" name="name" id="name"/>
		<br/>
		<label>密码：</label>
		<input type="password" name="password" id="passwordIn"/>
		<br/>
		<label>性别：</label>
		<input type="text" name="sex" id="sex"/>
		<br/>
		<select name="dormId">
			<c:forEach items="${dorms}" var="dorm">
				<option value="${dorm.id}">${dorm.name}</option>
			</c:forEach>
		</select>
		<label>门号：</label>
		<input type="text" name="room" id="room"/>
		<br/>
		<label>电话：</label>
		<input type="text" name="tel" id="tel"/>
		<br/>
		<button>注册</button>
		<br/>
	</form>
	
	<form action="managerRegister" method="post" id="manager">
		<h1>管理员注册</h1>
		<label>姓名:</label>
		<input type="text" name="name" id="name"/>
		<br/>
		<label>密码：</label>
		<input type="password" name="password" id="passwordIn"/>
		<br/>
		<select name="dormId">
			<c:forEach items="${dorms}" var="dorm">
				<option value="${dorm.id}">${dorm.name}</option>
			</c:forEach>
		</select>
		<label>电话：</label>
		<input type="text" name="tel" id="tel"/>
		<br/>
		<button>注册</button>
		<br/>
	</form>
	
	<script>
	$("#choose").click(function(){
		var status=$("input[name='status']:checked").val();
		if (status==0) {
			$("#manager").css("opacity",0);
			$("#student").css("opacity",1);
		} else {
			$("#student").css("opacity",0)
			$("#manager").css("opacity",1);
		}
	})
	</script>
	
</body>
</html>