<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>心理测评系统-管理系统-登录</title>
	<link rel="stylesheet" href="assets/styles/login.css" /> 
</head>
<body>
	<form class="login-form" action="Login${ requestScope.redirect }" method="post">
		<div class="login-background">
			<div class="login-panel">
				<div class="login-title">心理测评系统-管理系统</div>
				<div class="login-item-container">
					<div class="login-input-item">
						<span class="login-span">用户名：</span>
						<input class="login-input" type="text" name="userName" />
					</div>
					<div class="login-input-item">
						<span class="login-span">密码：</span>
						<input class="login-input" type="password" name="password" />
					</div>
					<div class="login-error-item">
						<p class="login-error">
							<c:out value="${ error }" />
						</p>
					</div>
				</div>
				<div class="login-tools">
					<div class="login-btn-container">
						<input class="login-btn" type="submit" value="登录" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>