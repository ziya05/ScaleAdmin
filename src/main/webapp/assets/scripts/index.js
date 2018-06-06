
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
	$(".index-data-item").click(function() {
			$(".scale-detail-content").text("");
			var id = $(this).find(".data-item-col:eq(0)").text();
			var scaleId = $(this).find(".data-item-col:eq(1)").text();
			var detailPanel = $(".scale-detail-panel");

			detailPanel.show(500, function() {
				$(".scale-detail-base-content").load(getProtocalHost() + "/ScaleAdmin/Detail?id=" + id + "&scaleId=" + scaleId + "&type=base");
				$(".scale-detail-advice-content").load(getProtocalHost() + "/ScaleAdmin/Detail?id=" + id + "&scaleId=" + scaleId + "&type=advice");
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
