
var zyAlert;
$(document).ready(function(){
	zyAlert = new ZyAlert();
	setSubmitEvent();
	setItemEvent();
	setDetailEvent();
});

function setSubmitEvent() {
	$(".btn-submit").click(function(){
		var btn = $(this);
		var pageIndexHidden = $("input[name=pageIndex]");
		var newIndex = parseInt(pageIndexHidden.val());
		
		if (btn.hasClass("index-condition-btn")) {
			newIndex = 1;
			
		} else if (btn.hasClass("zy-pages-prev")) {
			newIndex = newIndex - 1;
			
		} else if (btn.hasClass("zy-pages-next")) {
			newIndex = newIndex + 1;
			
		} else if (btn.hasClass("zy-pages-jump-btn")) {
			var inputVal = $(".zy-pages-jump-input").val();
			if (/^\d+$/.test(inputVal)) {
				newIndex = parseInt(inputVal);
			} else {
				zyAlert.show("页码格式不正确！");
				return;
			}
		}
		
		pageIndexHidden.val(newIndex);
		$(".index-form").submit();
	});
};

function setItemEvent() {
	var progress = new Progress($(".scale-detail-panel"));

	$(".index-data-item").click(function() {

			var id = $(this).find(".data-item-col:eq(0)").text();
			var scaleId = $(this).find(".data-item-col:eq(1)").text();
			var detailPanel = $(".scale-detail-panel");

			var baseUrl = getProtocalHost() + "/ScaleAdmin/Detail?id=" + id + "&scaleId=" + scaleId;
			var basePanel = $(".scale-detail-base-content")
				.text("");

			var advicePanel = $(".scale-detail-advice-content")
				.text("");

			detailPanel.show(500, function() {
				progress.showProgress("结果加载中，请稍后");
				basePanel.load(baseUrl + "&type=base", function(response, status, xhr) {
					if (status == "error") {
						progress.hideProgress();
						zyAlert.show("加载结果数据失败！", function(){
							detailPanel.hide(500);
						});
					} else {
						advicePanel.load(baseUrl + "&type=advice", function(response, status, xhr){
						if (status == "error") {
							progress.hideProgress();
							zyAlert.show("加载结果数据失败！", function(){
								detailPanel.hide(500);
							});
						} else {
							progress.hideProgress();
							$(".scale-detail-btn-userData").click();
						}
					});
					}
				});
				
			});
		});

	$(".scale-detail-btn-close").click(function() {
		var detailPanel = $(".scale-detail-panel");
		detailPanel.hide(500);
	});
};

function setDetailEvent() {
	$(".scale-detail-btn-userData").click(function() {
		$(".scale-detail-container > div").hide();
		$(".scale-detail-base").show(500);
	});

	$(".scale-detail-btn-advice").click(function() {
		$(".scale-detail-container > div").hide();
		$(".scale-detail-advice").show(500);
	});
};
