<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>


<div>
	<c:forEach var="item" items="${requestScope.adviceLst }" >
		<div class="testee-advice-item">
			<p class="testee-advice-factorName">
				${ item.factorName } - ${ item.formatScore } - ${ item.level }
			</p>
			<p class="testee-advice-description"><span>结果解释</span>-
				<pre class="testee-advice-content">${ item.description }</pre>
			</p>
			<p class="testee-advice-advice"><span>建议</span>-
				<pre class="testee-advice-content">${ item.advice }</pre>
			</p>
		</div>
	</c:forEach>
</div>
