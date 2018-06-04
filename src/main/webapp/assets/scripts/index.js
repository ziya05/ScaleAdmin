$(document).ready(function(){
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
			if (/^\d+$/.test) {
				newIndex = parseInt();
			} else {
				alert("页码格式不正确！");
			}
			
		}
		
		pageIndexHidden.val(newIndex);
		$(".index-form").submit();
	});
});

