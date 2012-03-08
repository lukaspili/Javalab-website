$(document).ready(function (){
	$('.first_connexion').click(function(){
		var text = $('.first');

		if(text.is(':hidden')){
			text.stop().fadeTo('normal', 1);
		}
		else{
			text.stop().fadeTo('normal', 0);
			text.css("display","none");
		}
	});
});