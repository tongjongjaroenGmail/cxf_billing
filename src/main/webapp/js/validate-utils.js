function validate( formId ){
	var require = $("#"+formId+" .require");
	for(var i=0;i <= require.size()-1;i++) {
		var it = require.eq(i);
		if($.trim(it.val()) == "" || (it.is("select") && it.val()=="")){
			var text;
			if(it.attr("title")){
				text = it.attr("title");
			}else{
				text = it.parent("td").prev().text().replace(/(\s*\*\s*)/g, '');
				//text = "ข้อมูล";
			}
			if( it.is("input") || it.is("textarea") ){
				showMessage("กรุณากรอก "+ text, it);
			}else if(it.is("select")){
				showMessage('กรุณาเลือก "'+ text +'" อย่างน้อย 1 รายการ', it);
			}
			var href =it.closest("[id*=tabs]").attr("aria-labelledby");
			if(href){
				$("#"+href).click();
			}
			require.eq(i).focus();
			//
			return false;
		}
		
	}
	return true;
}
$(function() {
	$("input,textarea,select").blur(function(){
		$(this).removeClass("invalidate");
	});
});

function showMessage(msg, obj){
	alert(msg);
	
	if(obj){
		//$(obj).addClass('invalidate');
		$(obj).focus();	
	}
}