$(function(){
	$(document).on('submit','#chatForm',function(){
		var text = $.trim($("#text").val());
		var name = $.trim($("#name").val()); 
		
		if(text != "" && name != ""){
			$.post('ChatPoster.php',{text: text, name: name},function(data){
				$(".chatMessages").append(data);
			});
		}else{
			alert("Data missing");
		}
		
	});
	
	function getMessages(){
		$.get('GetMessages.php',function(data){
			$(".chatMessages").html(data);
		});
	}
	setInterval(function(){
		getMessages();
	},500);
});