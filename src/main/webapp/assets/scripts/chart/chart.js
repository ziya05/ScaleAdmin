function chartController(autoInit) {
	
	var obj = null;
	var myCharts = [];
		
	this.init = function(containerObj) {
		
		if (typeof containerObj === 'undefined'
			|| !containerObj) {
			containerObj = $(".dataForChart");
		}
		
		obj = $.parseJSON(containerObj.text());
		containerObj.remove();
	}
	
	this.getObj = function() {
		return obj;
	}

	this.resizeChart = function() {
		if (myCharts) {
			$.each(myCharts, function(i, chart){
				if (chart) {
					chart.resize();
				}
			})
		}
	}
	
	this.setup = function(container, option) {
		var mychart = echarts.init(container);
		mychart.setOption(option, false);
		
		myCharts.push(mychart);
	}
	
	if (typeof autoInit === 'undefined' 
		|| autoInit == true) {
		this.init();
	}
}

var _chart = new chartController();

function resizeChart() {
	_chart.resizeChart();
}

function getObj() {
	return _chart.getObj();
}