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
	<h2>登录成功</h2>
	<jsp:include page="logout.jsp"></jsp:include>

	<%-- 已登录 --%>
	欢迎您,
	<shiro:user>
		<shiro:principal />
		<div style="float: right;">
			<a href="${pageContext.request.contextPath}/user/logout">登出</a>
		</div>
	</shiro:user>
	<shiro:guest>
		<a href="${pageContext.request.contextPath}/user/login">请登录</a>
	</shiro:guest>
	<shiro:guest>
        游客~~
    </shiro:guest>
	<hr>
	<shiro:hasRole name="admin">
		<a href="#"><shiro:principal/>操作</a>
	</shiro:hasRole>
	<shiro:lacksRole name="admin">
	    
		<a href="#"><shiro:principal/>操作</a>
	</shiro:lacksRole>

	<shiro:hasAnyRoles name="admin,guest">
		<a href="#">进班学习</a>
	</shiro:hasAnyRoles>

	<hr>
	<shiro:hasPermission name="user:query">
		<a href="#">收罚款</a>
	</shiro:hasPermission>
	<shiro:lacksPermission name="user:delete">
		<a href="#">进班学习</a>
	</shiro:lacksPermission>
</body>
</html>