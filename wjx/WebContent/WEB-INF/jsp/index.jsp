<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>请输入一个整数数组，用'，'隔开</h4>
	<s:actionerror/>
	<form action="index_calculate.action" method="post">
	输入数组：<input type="text" name="inList"><br>
	<input type="submit" value="提交">
	</form>
</body>
</html>