<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<div>
	<link rel="stylesheet" href="assets/styles/chart.css" />
	<script src="assets/scripts/chart/echarts.min.js"></script>
	<script src="assets/scripts/chart/chart.js"></script>
	<script src="assets/scripts/chart/chart1202.js"></script>
	<script>
		$(document).ready(function() {
			var obj = getObj();
			
			loadENChart($(".en-chart")[0]);
			
			loadTotalChart($(".total-chart")[0]);
		})
	</script>
	
	<div class="chart-block">
		<div class="chart-panel en-chart chart-panel-square"></div>
		<p class="chart-remark">
    注： 本图为 E 量表与 N 量表关系图。图中坐标轴上的数字为Z分数。 通过 Z = 1 与 Z = -1 各做一条直线， 两线之间区域内包括总体70%的人数。
    对于 E 量表来说， 若分数落入此间， 可以认为受试者属于中间型。 E 分数若落入两线以外区域， 可以认为是属于内向或外向。 N 分数以此类推。
    红点为受试者的 E 分与 N 分的交点， 由此可了解受试者的个性特点。	
		</p>
	</div>
	
	<div class="dataForChart">${ requestScope.fslLst }</div>
	
	<div class="chart-block">
		<div class="chart-panel total-chart chart-panel-square"></div>
		<p class="chart-remark">
注： 本图为 P，E，N，L 四量表剖析图。图中左右两边框外数字为Z分数。通过 Z = 1 与 Z = -1 各做一条直线，两线之间区域内包括总体70%的人数。
对于 E 量表来说，若分数落入此间，可以认为受试者属于中间型。 E 分数若落入两线以外区域， 可以认为属于内向或外向。 其他量表类推。
		</p>
	</div>
</div>