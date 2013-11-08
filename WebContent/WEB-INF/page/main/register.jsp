<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<link rel="stylesheet" href="common/js/jquery/plugins/jquery-ui/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
<script type="text/javascript" src="common/js/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="common/js/jquery/plugins/jquery-ui/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript">
  	$(function(){
  		$( "#datepicker" ).datepicker({
  	      showOn: "button",
  	      buttonImage: "common/images/icon/calendar.gif",
  	      buttonImageOnly: true
  	    });
		$("#subform").on("click",function subForm(){
			$("form:first").submit();
  	  	});
  	 });
</script>
<body>
	<h1 align="center">注册</h1>
	<div align="center">
		<form action="member.do" method="post">
			<input type="hidden" name="method" value="doRegister"/>
			<p>真实姓名：<input type="text" id="name" name="name"/></p>
			<p>用&nbsp;户&nbsp;名：<input type="text" id="username" name="username"/></p>
			<p>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="text" id="password" name="password"/></p>
			<p>&nbsp;&nbsp;出生日期：<input type="text" id="datepicker" name="bornDate"/></p>
			<input type="button" id="subform" value="注册"/>&nbsp;<input type="reset"" value="重写"/>
		</form>
	</div>
</body>
</html>