<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>快速测试</title>
	<script src="assets/scripts/jquery-3.3.1.min.js"></script>
	<script>
		function clearInputs() {
			
			var questionInput = $(".questionInput");
			questionInput.each(function(index) {
				$(this).val("");
			})
		}
		
		function quickInput(text) {

			var vals = text.split(/\t|\n/);
			
			var questionInput = $(".questionInput");
			var inputSize = questionInput.length;
			var valsSize = vals.length;
			
			for(var i = 0; i < inputSize && i < valsSize; i++) {
				questionInput.eq(i).val(vals[i]);
			}
		}
	
		$(document).ready(function() {
			$("#btnClear").click(function() {
				$("#answerConent").val("");
				clearInputs();
			})
						
			$("#scaleSelect").change(function() {
				$("#getform").submit();
			})
			
			$("#answerConent").change(function(){
				var text = $("#answerConent").val().trim();
				if (text != "") {
					clearInputs();
					quickInput(text);
				}
			})
		})
	</script>
	<style>
		* {
			margin: 0;
			padding: 0;
			box-sizing: border-box;
			line-height: 1.8;
		}
		.left, .right {
			display: inline-block;
			vertical-align: top;
			margin: 20px 0;
		}
		
		.left {
			width: 400px;
		}
		
		.right {
			width: calc(100% - 450px);
		}
		
		.baseInfo, .extendInfo {
			margin-bottom: 20px;
		}
		
		.attr {
			width: 40%;
			display: inline-block;
			margin-top: 10px;
		}
		
		.questionHeadRow, .questionDataRow {
			display: flex;
			width: 100%;
		}
		
		.rowHead, .questionTitle, .questionBlock {
			display: inline-block;
			width: calc(100% / 11);
		}
		
		.questionInput {
			width: 100%;
			height: 100%;
		}
		
		#answerConent {
			width: 100%;
			height: 300px;
			margin: 20px;
		}
		
		.textInputPanel, .quickPastePanel {
			margin: 20px 0;
		}
		
		.textQuestionText {
			width: 300px;
			height: 100px;
		}
		
		.btn {
			width: 100px;
			height: 30px;
			margin: 20px 0;
		}
		
		.mainBtn {
			width: 300px;
			height: 30px;
		}
		
	</style>
</head>
<body>
	<div class="left">
		<form id="getform" method="get" action="QuickTest">
			<select id="scaleSelect" name="scale">
				<c:forEach var="scale" items="${ requestScope.scales }">
					<c:choose>
						<c:when test="${ requestScope.scale.id == scale.id }" >
							<option value="${ scale.id }" selected>${ scale.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${ scale.id }" >${ scale.name }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</form>
	
		<div>
			<label>当前选中的量表为： ${ requestScope.scale.name }</label>
		</div>
	</div>
	<div class="right">
		<form method="post" action="QuickTest">
			<input type="hidden" value="${ requestScope.scale.id }" name="scaleId" />
			<div class="baseInfo">
				<div class="attr">
					<lable>名字：</lable>
					<input type="text" name="name" />
				</div>
				<div class="attr">
					<lable>性别：</lable>
					<input type="text" name="gender" />
				</div>
				<div class="attr">
					<lable>年龄：</lable>
					<input type="text" name="age" />
				</div>
			</div>
			<div class="extendInfo">
				<c:forEach var="item" items="${ requestScope.info.items }">
					<div class="attr">
						<label>${ item.title }：</label>
						<input type="text" data-name="${ item.name }" name="attr"/>
					</div>
				</c:forEach>
			</div>
			<div class="questionPanel">
				<div class="questionHeadRow">
					<div class="rowHead"></div>
					<c:forEach var="i" begin="1" end="10" >
						<div class="questionTitle">
							${ i }
						</div>
					</c:forEach>
				</div>
				
				<c:forEach var="item" items="${ requestScope.scale.items }">
					<c:if test="${ item.id % 10 == 1 }">
						<div class="questionDataRow">
							<div class="rowHead">
								<fmt:formatNumber value="${ item.id / 10 }"
									type="number" pattern="#" />
							</div>
					</c:if>
				
							<div class="questionBlock">
								<input type="text" class="questionInput" name="answer" />
							</div>
					
					<c:if test="${ item.id % 10 == 0 }">
						</div>
					</c:if>
				</c:forEach>

			</div>
			
			<c:if test="${ requestScope.textQuestionLst.size() > 0 }">
				<div class="textInputPanel">
					<p>文本问题项输入区</p>
					<c:forEach var="item" items="${ requestScope.textQuestionLst }" >
						<div class="questionDataRow">
							<label class="textQuestionLabel">${ item.id }</label>
							<textarea name="textQuestion" class="textQuestionText" ></textarea>
						</div>
					</c:forEach>
				
				</div>
			</c:if>
			
			<div class="quickPastePanel">
				<p>快速粘贴区</p>
				<textarea id="answerConent" ></textarea>
			</div>
			
			<div>
				<input type="button" class="btn" value="应用" />
				<input id="btnClear" type="button" class="btn" value="清空" />
			</div>
			
			<input type="submit" value="提交" class="mainBtn" />
		</form>
		
	</div>
	<div>
		${ requestScope.result }
	</div>
</body>
</html>