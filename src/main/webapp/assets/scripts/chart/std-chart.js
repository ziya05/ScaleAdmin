
function loadLineChart(container, title, keyArr, valArr) {
	var option = getLineOrBarOption(title, keyArr, valArr, 'line');
	_chart.setup(container, option);
}

function loadBarChart(container, title, keyArr, valArr) {
	var option = getLineOrBarOption(title, keyArr, valArr, 'bar');
	_chart.setup(container, option);
}

function getLineOrBarOption(title, keyArr, valArr, chartType) {
	var option = {
			title : {
		        text: title,
		        subtext: '-因子分数',
		        x:'center',
		        textStyle: {
		        	color: 'rgb(227, 143, 45)'
		        },
		        subtextStyle: {
		            color: '#fff'
		        }
	    	},
			xAxis: {
		        type: 'category',
		        data: keyArr,
		        axisLabel: {
		        	interval: 0,
		        	rotate: -30,
		        	textStyle: {
		        		color: '#fff',
		        		fontSize: '14'
		        	}
		        },
		        axisLine: {
		            lineStyle: {
		                color: '#fff',
		                width: 3,
		            }
		        }
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		        	interval: 0,
		        	rotate: -30,
		        	textStyle: {
		        		color: '#fff',
		        		fontSize: '14'
		        	}
		        },
		        axisLine: {
		            lineStyle: {
		                color: '#fff',
		                width: 3,
		            }
		        }
		    },
		    series: [{
		        data: valArr,
		        type: chartType,
		        lineStyle: {
		            color: 'rgb(227, 143, 45)',
		        },
		        areaStyle: {
		        	color: 'rgb(227, 143, 45)',
		        },
		    }],
		    tooltip: {
		        show: true
		    }
		};

	return option;
}

function loadPieChart(container, title, keyArr, dataArr) {
	var option = getPieOrCircleOption( 
			title, 
			keyArr, 
			dataArr, 
			'55%',
			true,
			true,
			'outside');
	_chart.setup(container, option);
}

function loadCircleChart(container, title, keyArr, dataArr) {
	var option = getPieOrCircleOption( 
			title, 
			keyArr, 
			dataArr, 
			['50%', '70%'],
			false,
			false,
			'center');
	_chart.setup(container, option);
}

function getPieOrCircleOption(title, keyArr, 
		dataArr, radius, avoidLabelOverlap,
		labelShow, labelPosition) {
	var option = {
		title : {
		        text: title,
		        subtext: '-因子分数',
		        x:'center',
		        textStyle: {
		        	color: 'rgb(227, 143, 45)'
		        },
		        subtextStyle: {
		            color: '#fff'
		        }
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}"
	    },
	    legend: {
	        type: 'scroll',
	        orient: 'vertical',
	        right: 10,
	        top: 20,
	        bottom: 20,
	        data: keyArr,
	        textStyle: {
	            fontSize: '14',
	            color: '#fff'
	        },
	        formatter: function (name) {
				return (name.length > 8 ? (name.slice(0,8)+"...") : name ); 
			},
	    },
	    series : [
	        {
	            name: '因子分',
	            type: 'pie',
	            radius : radius,
	            center: ['40%', '50%'],
	            data: dataArr,
	            avoidLabelOverlap: avoidLabelOverlap,
	            label: {
	                normal: {
	                	show: labelShow,
	                	position: labelPosition,
	                    textStyle: {
	                        fontSize: '14',  
	                    },
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	
	return option;
}