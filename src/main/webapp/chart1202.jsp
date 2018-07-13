<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<div>
	<script src="assets/scripts/echarts.min.js"></script>
	<script src="assets/scripts/chart.js"></script>
	<script>
		$(document).ready(function() {
			var data = getObj();
		})
	</script>
	
	<div class="dataForChart">${ requestScope.fslLst }</div>
</div>