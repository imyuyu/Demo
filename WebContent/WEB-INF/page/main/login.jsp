<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript" src="common/js/jquery/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		$("#subform").on("click",function subForm(){
			$("form:first").submit();
  	  	});
	});
</script>
<body>
	<h1 align="center">登錄</h1>
	<div align="center">
		<form action="member.do" method="post">
			<input type="hidden" name="method" value="doLogin"/>
			<span style="color: red;">${msg}</span>
			<p>用&nbsp;户&nbsp;名：<input type="text" id="username" name="username"/></p>
			<p>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="text" id="password" name="password"/></p>
			<input type="button" id="subform" value="登錄"/>&nbsp;<input type="reset"" value="重写"/>
		</form>
	</div>
</body>
</html>