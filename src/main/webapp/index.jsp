<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
	<title>心理测评系统-管理系统</title>
	<link rel="stylesheet" href="assets/styles/zy-alert.css" />
	<link rel="stylesheet" href="assets/styles/index.css" /> 
	<script src="assets/scripts/jquery-3.3.1.min.js"></script>
	<script src="assets/scripts/zy-alert.js"></script>
	<script src="assets/scripts/index.js" ></script>
</head>
<body>
	<form class="index-form" action="Index" method="post">
		<div class="index-mask"></div>
		<div class="index-background">
			<div class="index-panel">
				<div class="index-condition-container">
					<div class="index-condition-item">
						<span class="index-condition-title">名字：</span>
						<input class="index-condition-input" name="userName" type="text" value="${ userName }" />
					</div>
					<div class="index-condition-item">
						<span class="index-condition-title">日期：</span>
						<input class="index-condition-input" name="date" type="text" value="${ date }" />
					</div>
					<div class="index-condition-item">
						<span class="index-condition-title">量表：</span>
						<input class="index-condition-input" name="scaleName" type="text" value="${ scaleName }" />
					</div>
					<div class="index-condition-btn btn-submit">查询</div>
				</div>
				<div class="index-data">
					<div class="index-data-panel">
						<c:forEach var="testeeDataBase" items="${requestScope.dataLst }" >
							<div class="index-data-item">
								<div class="data-item-col">${ testeeDataBase.id }</div>
								<div class="data-item-col">${ testeeDataBase.scaleId }</div>
								<div class="data-item-col">${ testeeDataBase.scaleName }</div>
								<div class="data-item-col">${ testeeDataBase.userName }</div>
								<div class="data-item-col">${ testeeDataBase.gender }</div>
								<div class="data-item-col">${ testeeDataBase.formatTime }</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="index-foot">
					<div class="zy-pages">
						<div class="zy-pages-btn zy-pages-prev btn-submit"></div>
						<div class="zy-pages-display">
							<span>${ pageIndex }</span>
							<span>/</span>
							<span>${ pageCount }</span>
							<span>-</span>
							<span>${ itemsCount }</span>
						</div>
						<div class="zy-pages-btn zy-pages-next btn-submit"></div>
						<div class="zy-pages-jump">
							<input class="zy-pages-jump-input" name="pageNumber" type="text" />
							<div class="zy-pages-btn zy-pages-jump-btn btn-submit" ></div>
						</div>
					</div>
				</div>
				<div>
					<input type="hidden" name="pageIndex" value="${ pageIndex }"/>
				</div>
			</div>
			<div class="scale-detail-panel">
				<div class="scale-detail-btn scale-detail-btn-close" title="关闭"></div>
				<div class="scale-detail-btn scale-detail-btn-userData" title="查看基本数据"></div>
				<div class="scale-detail-btn scale-detail-btn-advice" title="查看解释及建议"></div>
				<div class="scale-detail-btn scale-detail-btn-copy" title="拷贝到剪切板"></div>
				<div class="scale-detail-container">
					<div class="scale-detail-base">
						<p class="scale-detail-title">用户基础数据</p>
						<div class="scale-detail-content scale-detail-base-content"></div>
					</div>
					<div class="scale-detail-advice">
						<p class="scale-detail-title">结果解释及建议</p>
						<div class="scale-detail-content scale-detail-advice-content"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
