<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type="text/javascript">
function go(type){
	var formObj = document.createElement("form");
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("value",type);
	input.setAttribute("name","method");
	formObj.appendChild(input);
	formObj.action="member.do";
	formObj.method="Post";
	formObj.submit();
}
</script>
<body>
	<a href="javascript:go('toLogin')" type="_self">Login</a>
	<a href="javascript:go('toRegister')" type="_self">Register</a>
</body>
</html>