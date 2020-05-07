<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pagesContext.request.contextPath }/user/login"
		method="post">
		username:<input type="text" name="username" value='<shiro:principal/>'><br>
		password:<input type="text" name="password"><br> <input
			type="submit" value="提交">
		<jsp:include page="logout.jsp"></jsp:include>
	</form>
</body>
</html>