<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<div>
	<style>
		.nochart-container {
			width: 100%;
			height: 500px;
			display: flex;
    		align-items: center;
    		justify-content: center;
		}
		.nochart-tip {
			font-size: 26px;
		}
	</style>

	<div class="nochart-container">
		<p class="nochart-tip">
			本量表(${ requestScope.scaleName })没有数据图！
		</p>
	</div>

</div>