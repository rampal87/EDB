$(document)
.ready(
		function() {
	

	/**
	 * This function will be used to load sub tab pages
	 */	

	$(".subtabs").unbind("click").on("click",function(){
		var tabId=$(this).attr('id');
		
		$("#"+tabId).parent().css({
			"background" : "white",
			"border" : "1px solid black",
			"border-bottom" : "none"
			} );

		$('#subtab-container > div > a').map(function() {
		   var allTabIds=this.id;
		   if(tabId!=allTabIds){
			   $("#"+allTabIds).parent().css({
					"background" : "#94DAF0",
					"border-top" : "none",
					"border-left" : "none",
					"border-bottom" : "1px solid black"
					}  );
		   }
		});

		var href=$(this).attr('href');
		$.ajax({
			type : "POST",
			url : href,
			beforeSend:function(){
			  },
			success : function(xhr) {	
				$("#mainContainer").html(xhr);	
			},
			error : function(xhr) {	
				$("#mainContainer").html("Application error! Please call help desk.");
			}
		});	
		return false;
	});
});
