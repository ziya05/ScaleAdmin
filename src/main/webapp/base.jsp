<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div class="testee-user-base">
			<span>被试名：</span>
			<span>${ baseData.userName }</span>
			<span>性别：</span>
			<span>${ baseData.gender }</span>
			<span>测试时间：</span>
			<span>${ baseData.testTime }</span>
		</div>
		<div class="testee-personal-info">
			<c:forEach var="item" items="${requestScope.baseData.personalInfo.items }" >
				<div class="testee-personal-info-item">
					<span>${ item.title }</span>
					<span>${ item.content }</span>
				</div>
			</c:forEach>
		</div>
		<div class="testee-groups">
			<span>所属团体：</span>
			<span>${ baseData.groups }</span>
		</div>
		<div class="testee-data">
			<c:forEach var="item" items="${requestScope.baseData.items }" >
				<div class="testee-data-item">
					<span>${ item.questionId }</span>
					<span>${ item.optionId }</span>
					<span>${ item.score }</span>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>