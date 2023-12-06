(function($) {
	/*------------------
	 Single Product
	 --------------------*/
	$('.product-thumbs-track > .pt').on('click', function() {
		$('.product-thumbs-track .pt').removeClass('active');
		$(this).addClass('active');
		var imgurl = $(this).data('imgbigurl');
		var bigImg = $('.product-big-img').attr('src');
		if (imgurl != bigImg) {
			$('.product-big-img').attr({
				src : imgurl
			});
			$('.zoomImg').attr({
				src : imgurl
			});
		}
	});

	$('.product-pic-zoom').zoom();

})(jQuery);

/*------------------
 Accordions
 --------------------*/
$('.panel-link').on('click', function(e) {
	$('.panel-link').removeClass('active');
	var $this = $(this);
	if (!$this.hasClass('active')) {
		$this.addClass('active');
	}
	e.preventDefault();
});



$('.price').on('click',function(){
	if($('#lb').val()=="" && $('#ub').val()==""){
		$('#lb').remove();
		$('#ub').remove();
	}
});


$('#price').on('click',function(){
	
	document.getElementById('form').submit();
});
