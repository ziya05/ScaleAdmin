
// dataPoint为EN的Z分数数组， 索引0为E量表的Z分数， 索引1为N量表的Z分数
function loadENChart(container, dataPoint) {

	var minVal = getMin(-3, dataPoint[0], dataPoint[1]);
	var maxVal = getMax(3, dataPoint[0], dataPoint[1]);

	var optionEN = getENOption(minVal, maxVal, dataPoint);
	_chart.setup(container, optionEN);
}

// pointArr为ENPL数组， 顺序应为E, N, P, L
function loadTotalChart(container, pointArr) {

	var dataPointArr = [];
	
	for (var i = 0; i < pointArr.length; i++) {
		dataPointArr.push([i + 1, pointArr[i]]);
	}
	
	var minVal = getMin(-3, getMin.apply(this, pointArr));
	var maxVal = getMax(3, getMax.apply(this, pointArr));
	
	var optionTotal = getTotalOption(minVal, maxVal, dataPointArr);
	_chart.setup(container, optionTotal);
}

function getENOption(minVal, maxVal, dataPoint) {

	var xAxisPoint = [];
	var yAxisPoint = [];
	
	var y1Line = [];
	var y2Line = [];
	
	var x1Line = [[-1, minVal], [1, minVal]];
	var x2Line = [[-1, maxVal], [1, maxVal]];
	
	for (var p = minVal; p <= maxVal; p++) {
		xAxisPoint.push([p, 0.0]);
		yAxisPoint.push([0.0, p]);
		y1Line.push([p, -1.0]);
		y2Line.push([p, 1.0]);
	}
	
	var axis = {
		type: 'value',
    	min: minVal,
    	max: maxVal,
    	interval: 1,
    	splitLine: { show: false },
    	axisLabel: {
    		show: false
    	},
    	nameTextStyle: {
    		color: '#fff',
    		fontSize: '16'
    	}
	}
	
	var axisPoint = {
        symbolSize: 15,
        itemStyle: {
        	normal: {
        		color: '#079'
        	}
        },
        label: {
                normal: {
                    show: true,
                    color: '#fff',
                    formatter: function(point) {
                    	if (point.data[0] == 0 
                    			&& point.data[1] == 0) {
                    		return '';
                    	}
                    	else if (point.data[0] == 0) {
                    		return point.data[1];
                    	} else {
                    		return point.data[0];
                    	}
                    },
                    fontSize: '16'
                }
        },
        type: 'scatter'
    };
	
	var xAxisPointForLabel = $.extend(true, {}, axisPoint, {
		data: xAxisPoint,
	});
	xAxisPointForLabel.label.normal.position = 'top';
	
	var yAxisPointForLabel = $.extend(true, {}, axisPoint, {
		data: yAxisPoint,
	});
	yAxisPointForLabel.label.normal.position = 'right';
	
	var polePoint = {
    	type: 'scatter',
    	symbolSize: 16,
    	itemStyle: {
        	normal: {
        		color: '#079'
        	}
        },
    	label: {
    		normal: {
    			show: true,
    			position: 'left',
    			color: '#fff',
    			fontSize: '16'
    		}
    	}
    };
	
	var topPole = $.extend(true, {}, polePoint, {
		data: [[0, maxVal]]
	});
	topPole.label.normal.position = 'top';
	topPole.label.normal.formatter = function(point) {
		return 'N';
	};

	var leftPole = $.extend(true, {}, polePoint, {
		data: [[minVal, 0]]
	});
	leftPole.label.normal.position = 'left';
	leftPole.label.normal.formatter = function(point) {
		return 'E';
	};

	var rightPole = $.extend(true, {}, polePoint, {
		data: [[maxVal, 0]]
	});
	rightPole.label.normal.position = 'right';
	rightPole.label.normal.formatter = function(point) {
		return 'E';
	};

	var bottomPole = $.extend(true, {}, polePoint, {
		data: [[0, minVal]]
	});
	bottomPole.label.normal.position = 'bottom';
	bottomPole.label.normal.formatter = function(point) {
		return 'N';
	};

	var option = {
			title: {
				text: 'EN剖析图',
				subtext: '-Z分数',
				left: 'right',
				textStyle: {
		        	color: 'rgb(227, 143, 45)'
		        },
		        subtextStyle: {
		            color: '#fff'
		        }
			},
		    xAxis: axis,
		    yAxis: axis,
		    series: [ xAxisPointForLabel,
		    	yAxisPointForLabel, 
		    	topPole,
		    	leftPole,
		    	rightPole,
		    	bottomPole, {
		    	type: 'line',
		    	areaStyle: { normal: {
		    		color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		                    offset: 0,
		                    color: 'rgb(129, 227, 238)'
		                }, {
		                    offset: 1,
		                    color: 'rgb(25, 183, 207)'
		                }])
		    	}},
		    	data: y1Line,
		    	lineStyle: {
		    		width: 0
		    	}
		    }, {
		    	type: 'line',
		    	areaStyle: { normal: {
		    		color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		                    offset: 0,
		                    color: 'rgb(25, 183, 207)'
		                    
		                }, {
		                    offset: 1,
		                    color: 'rgb(129, 227, 238)'
		                }])
		    	}},
		    	data: y2Line,
		    	lineStyle: {
		    		width: 0
		    	}
		    },{
		    	type: 'line',
		    	areaStyle: { normal: {
		    		color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		                    offset: 0,
		                    color: 'rgb(129, 227, 238)'
		                }, {
		                    offset: 1,
		                    color: 'rgb(25, 183, 207)'
		                }])
		    	}},
		    	data: x1Line,
		    	lineStyle: {
		    		width: 0
		    	}
		    }, {
		    	type: 'line',
		    	areaStyle: { normal: {
		    		orrgin: 'start',
		    		color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		                    offset: 0,
		                    color: 'rgb(25, 183, 207)'
		                    
		                }, {
		                    offset: 1,
		                    color: 'rgb(129, 227, 238)'
		                }])
		    	}},
		    	data: x2Line,
		    	lineStyle: {
		    		width: 0
		    	}
		    }, {
		    	symbolSize: 30,
		    	data: [
		    		dataPoint
		    	],
		    	type: 'scatter',
		    	label: {
		    		emphasis: {
		    			show: true,
		    			formatter: function(point) {
		    				return "E: " + point.data[0] + ", N: " + point.data[1];
		    			},
		    			position: 'right',
		    			fontSize: '18'
		    		}
		    	},
		    	color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		            offset: 0,
		            color: 'rgb(251, 118, 123)'
		        }, {
		            offset: 1,
		            color: 'rgb(204, 46, 72)'
		        }])
		    }]
		};
	return option;
}

function getTotalOption(minVal, maxVal, dataPointArr) {

	var factorCount = 5;
	
	var xAxisPoint = [];
	for (var p = 0; p <= factorCount; p++) {
		xAxisPoint.push(p);
	}
	
	var x1LineData = [];
	var x2LineData = [];
	
	var y1LineData = [
		[0, 1],
        [factorCount, 1],
	];
	var y2LineData = [
		[0, -1],
        [factorCount, -1],
	];
	
	var xLabelFunction = function(point) {
        
        if (point == 1) {
            return 'E'
        } else if (point == 2) {
            return 'N'
        } else if (point == 3) {
            return 'P'
        } else if (point == 4) {
            return 'L'
        } else {
            return ''
        }
           
    };
	
	for (var p = minVal; p <= maxVal; p++) {
		x1LineData.push([0, p]);
		x2LineData.push([factorCount, p]);
	}
	
	var centerLineData = [
		[0, 0],
        [factorCount, 0]
	];
	
	var option = {
	    title: {
	    	text: '总剖析图',
			subtext: '-Z分数',
			left: 'right',
			textStyle: {
	        	color: 'rgb(227, 143, 45)'
	        },
	        subtextStyle: {
	            color: '#fff'
	        }
	    },
	    xAxis: {
	       data:xAxisPoint,
	        type: 'value',
	        splitLine: {
	            show: true
	        },
	        axisLine: {
	            show: false
	        },
	        axisLabel: {
	                position: 'right',
	                formatter: xLabelFunction,
	                color: '#fff',
	                fontSize: '16',
	        }
	    },
	    yAxis: {
	        min: minVal,
	        max: maxVal,
	        interval: 1,
	        type: 'value',
	        splitLine: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	        axisLabel: {
	            formatter: function() {
	                return ''
	            }
	        }
	    },

	    series: [
	        {
	            data: centerLineData,
	            type: 'line',
	            lineStyle: {
	                color: '#079'
	            }
	        },
	        {
	             data: x2LineData,
	            type: 'scatter',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'right',
	                    formatter: function(point) {
	                        return point.data[1]
	                    },
	                    color: '#fff',
	                    fontSize: '16',
	                }
	            },
	            itemStyle: {
	            	normal: {
	            		color: '#079'
	        	}
	        }
	        },
	         {
	             data: x1LineData,
	            type: 'scatter',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'left',
	                    formatter: function(point) {
	                        return point.data[1]
	                    },
	                    color: '#fff',
	                    fontSize: '16',
	                }
	            },
	            itemStyle: {
	            	normal: {
	            		color: '#079'
	            	}
	            }
	        },
	        {
	            type: 'line',
	            data: y1LineData,
	            areaStyle: { normal: {
			    		color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(129, 227, 238)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(25, 183, 207)'
			                }])
			    	}},
			    	lineStyle: {
			    		width: 0
			    	}
	        },
	        {
	            type: 'line',
	            data: y2LineData,
	            areaStyle: { normal: {
			    		color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(129, 227, 238)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(25, 183, 207)'
			                }])
			    	}},
			    	lineStyle: {
			    		width: 0
			    	}
	        },
	        {
		    	symbolSize: 30,
		    	data: dataPointArr,
		    	type: 'scatter',
		    	label: {
		    		emphasis: {
		    			show: true,
		    			formatter: function(point) {
		    				return  xLabelFunction(point.data[0]) + ": " + point.data[1];
		    			},
		    			position: 'right',
		    			fontSize: '18'
		    		}
		    	},
		    	color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		            offset: 0,
		            color: 'rgb(251, 118, 123)'
		        }, {
		            offset: 1,
		            color: 'rgb(204, 46, 72)'
		        }])
		    },
	    ]
	};
	
	return option;
}

function getMax() {

	var count = arguments.length;
	var data = [];
	for (var i = 0; i < count; i++) {
		data.push(Math.ceil(arguments[i]))
	}
	
	return Math.max.apply(this, data);
}

function getMin() {
	var count = arguments.length;
	var data = [];
	for (var i = 0; i < count; i++) {
		data.push(Math.floor(arguments[i]));
	}
	
	return Math.min.apply(this, data);
}