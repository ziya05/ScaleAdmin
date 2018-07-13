<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<div>
	<style>
		.dataForChart { width: 0; height: 0; display: none; }
		.chart-block { width: 100%; margin: 50px 0; padding-bottom: 50px; border-bottom: 1px solid rgb(227, 143, 45); }
		.chart-panel { width: 100%; height: 500px; }
	</style>
	<script src="assets/scripts/echarts.min.js"></script>
	<script src="assets/scripts/chart.js"></script>
	<script src="assets/scripts/std-chart.js"></script>
	<script>
	
		$(document).ready(function() {
			var obj = getObj();

			var keyArr = new Array(obj.length);
			var valArr = new Array(obj.length);
			var dataArr = new Array(obj.length);
			
			$.each(obj, function(i, item) {
				keyArr[i] = item.name;
				valArr[i] = item.formatScore;
				dataArr[i] = {
						name: item.name,
						value: item.formatScore
				};
			});
			
			loadLineChart($(".line-chart")[0], "剖析图", keyArr, valArr);
			
			loadBarChart($(".bar-chart")[0], "直方图", keyArr, valArr);
			
			loadPieChart($(".pie-chart")[0], "饼图", keyArr, dataArr);

			loadCircleChart($(".circle-chart")[0], "环图", keyArr, dataArr);
			
		});
			
	</script>

	<div class="chart-block">
		<div class="chart-panel line-chart"></div>
	</div>
	
	<div class="chart-block">
		<div class="chart-panel bar-chart"></div>
	</div>
	
	<div class="chart-block">
		<div class="chart-panel pie-chart"></div>
	</div>
	
	<div class="chart-block">
		<div class="chart-panel circle-chart"></div>
	</div>
	
	<div class="dataForChart">${ requestScope.fslLst }</div>
</div>