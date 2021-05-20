<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</head>
<body>

	<h1>请登录</h1>
	<form action="login/status" method="post">
		<label>用户名：</label>
		<input type="text" name="userName" id="userNameIn"/>
		<span id="name"></span>
		<br/>
		<label>密码：</label>
		<input type="password" name="password" id="passwordIn"/>
		<span id="password"></span>
		<br/>
		<input type="radio" name="status" value="学生">学生
		<input type="radio" name="status" value="管理员">管理员
		<input type="radio" name="status" value="系统管理">系统管理
		<button disabled>登录</button>
		<br/>
	</form>
	
	<a href="register">免费注册</a>
	
	
	<script>
		
		$("#userNameIn").on("blur",function(){
			var status=$("input[name='status']:checked").val();
			console.log(status);
			$.ajax({
				url: '${pageContext.request.contextPath}/login/matchName',
				type: 'post',
				data: {"userName":$("#userNameIn").val(),"status":status},
				success: function(data) {
					if(data==false){
						if (typeof(status)=="undefined") {
							$("#name").html("请勾选用户身份");
						} else {
							$("#name").html(status+"不存在该用户");
						}
						
						
					}else{
						
						$("#name").html("用户名正确");
					}					
				},
			});
		})
		
		$("#passwordIn").change(function(){
			var status=$("input[name='status']:checked").val();
			$.ajax({
				url: '${pageContext.request.contextPath}/login/matchUser',
				type: 'post',
				data: {"userName":$("#userNameIn").val(), "password":$('[name="password"]').val(), "status":status
				},
				success: function(reData) {
					if(reData==false){
						
						$("#password").html("密码错误");
					}else{
						
						$("#password").html("密码正确");
						if($("#name").html()=="用户名正确"){
							$('button').attr('disabled',false)
						}
					}
				},
			});
		})
	</script>
	
</body>
</html>