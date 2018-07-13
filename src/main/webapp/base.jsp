<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>


<div>
	<div class="testee-user-base">
		<span>量表名称：</span>
		<span>${ baseData.scaleName }</span>
		<span>被试名：</span>
		<span>${ baseData.userName }</span>
		<span>性别：</span>
		<span>${ baseData.gender }</span>
		<span>年龄：</span>
		<span>${ baseData.formatAge }</span>
		<span>测试时间：</span>
		<span>${ baseData.formatTime }</span>
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
		<div class="testee-data-option">
			<p class="testee-data-title">
				选项列表：
			</p>
			<c:forEach var="item" items="${requestScope.baseData.items }">
				<c:if test="${item.questionId % 10 == 1}">
					<div class="testee-data-row">
						<span class="testee-data-row-head">
							<fmt:formatNumber type="number" value="${ (item.questionId /10) * 10 }" pattern="000" maxFractionDigits="0" />
							---
							<fmt:formatNumber type="number" value="${ (item.questionId / 10 + 1) * 10 - 1 }" pattern="000" maxFractionDigits="0" />
						</span>
				</c:if>
				<span class="testee-data-row-block" title="${ item.optionId }">
					${ item.optionId }
				</span>
				<c:if test="${item.questionId % 10 != 0 && item.questionId % 5 == 0}">
					<span class="testee-data-row-split"></span>
				</c:if>
				<c:if test="${item.questionId % 10 == 0}">
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${ requestScope.baseData.items.size() % 10 != 0 }">
				</div>
			</c:if>
		</div>
		<div class="testee-data-score">
			<p class="testee-data-title">
				选项得分：
			</p>
			<c:forEach var="item" items="${requestScope.baseData.items }">
				<c:if test="${item.questionId % 10 == 1}">
					<div class="testee-data-row">
						<span class="testee-data-row-head">
							<fmt:formatNumber type="number" value="${ (item.questionId /10) * 10 }" pattern="000" maxFractionDigits="0" />
							---
							<fmt:formatNumber type="number" value="${ (item.questionId / 10 + 1) * 10 - 1 }" pattern="000" maxFractionDigits="0" />
						</span>
				</c:if>
				<span class="testee-data-row-block" title="${ item.score }">
					${ item.score }
				</span>
				<c:if test="${item.questionId % 10 != 0 && item.questionId % 5 == 0}">
					<span class="testee-data-row-split"></span>
				</c:if>
				<c:if test="${item.questionId % 10 == 0}">
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${ requestScope.baseData.items.size() % 10 != 0 }">
				</div>
			</c:if>
		</div>
	</div>
	<div class="testee-factor-score-level">
		<p class="testee-data-title">
			因子得分及等级：
		</p>
		<c:forEach var="item" items="${requestScope.fslLst }">
			<div class="testee-data-row">
				<span class="testee-data-row-index">
					${ item.factorId }
				</span>
				<span class="testee-data-row-head" title="因子名称：${ item.name }">
					${ item.name }
				</span>
				<span class="testee-data-row-block" title="分数：${ item.score }">
					${ item.formatScore }
				</span>
				<span class="testee-data-row-block" title="等级：${ item.level }">
					${ item.level }
				</span>
			</div>
		</c:forEach>
	</div>
</div>
