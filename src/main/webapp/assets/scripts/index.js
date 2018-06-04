var zyAlert;
$(document).ready(function(){
	zyAlert = new ZyAlert();

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
});

