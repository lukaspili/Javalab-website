jQuery(document).ready(function($) {
    $('.lightbox-message').click(function(e) {
        // e.preventDefault();
 			
 			var nom = $(this).attr("alt");

 			if ($('#message').length > 0) { 
 
	        	$('#message').show();
        	}
        	else{
	            var message =
	            '<div id="message">' +
	                '<section class="message-main">' + 
	                	'<div class="message-text">' +
	                		'<a class="close" style="z-index:100"><img src="/public/images/icon/cross.png"></a>' +
	                		'<img src="/public/images/icon/send.png">' +
	                		'<h3>Envoyer un message</h3>' +
	                		'<p>Vous souhaitez envoyer un message à <strong>' + nom + '</strong>.</p>' +
	                	'</div>'+
	                	'<div class="message-form">' +
	                		'<form method="post" action="#">' +
	                			'<input type="text" value="Votre nom">' +
	                			'<textarea>Votre message</textarea>' +
	                			'<input type="submit" class="submit">' +
	                		'</form>' +
	                	'</div>'+
	                '</section>' +
	            '</div>';
	 
	            $('body').append(message);
	        }
 
    });
 
    
   $('.close').live('click', function() { //must use live, as the lightbox element is inserted into the DOM
        $('#message').hide();
    });
 
});