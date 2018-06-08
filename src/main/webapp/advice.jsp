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
		<c:forEach var="item" items="${requestScope.adviceLst }" >
			<div class="testee-advice-item">
				<p class="testee-advice-factorName">
					${ item.factorName } - ${ item.score } - ${ item.level }
				</p>
				<p class="testee-advice-description"><span>结果解释</span>-${ item.description }</p>
				<p class="testee-advice-advice"><span>建议</span>-${ item.advice }</p>
			</div>
		</c:forEach>
	</div>
</body>
</html>