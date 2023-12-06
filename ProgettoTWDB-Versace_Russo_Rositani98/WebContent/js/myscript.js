$('#price').on('click',function(){
	if($('#lb').val()=="" && $('#ub').val()==""){
		$('#lb').removeAttr("name");
		$('#ub').removeAttr("name");
	}else if ($('#lb').val()=="") {
		$('#lb').removeAttr("name");
	}else if ($('#ub').val()==""){
		$('#ub').removeAttr("name");
	}
	
	$("#form").submit();
	
});


//$('.nice-select').on('click',function(){
//	var e = document.getElementById("category-elem");
//	var value = e.value;
//	var text = e.options[e.selectedIndex].text;;
//
//	console.log(value);
//	console.log(text);
//});


$('.btnn').on('click',function(){
//	console.log("dentro1");
//	
//	var e = document.getElementById("category-elem");
//	var value = e.value;
//	var text = e.options[e.selectedIndex].text;
//	
//	console.log(value);
//	console.log(text);
//	
	var f = document.querySelector("#search-form");
//	
//	if(value != 0 && text.toUpperCase() != "ALL CATEGORY"){
//		let input = document.createElement("input");
//		input.type="hidden";
//		input.name="category";
//		input.value=value;
//		f.prepend(input);
//		console.log("ho aggiunto");
//	}
	
//	alert("fine");
	
	f.submit();
});

$('#category-elem').on('change',function(){
	console.log("dentro1");
	
	var e = document.getElementById("category-elem");
	var value = e.value;
	var text = e.options[e.selectedIndex].text;
	
	console.log(value);
	console.log(text);
	
	var f = document.querySelector("#search-form");
	
	var i = $("#search-form").parent().find("input[type=hidden]");
	i.remove();
	
	if(value != 0 && text.toUpperCase() != "ALL CATEGORY"){
		
		let input = document.createElement("input");
		input.type="hidden";
		input.name="category";
		input.value=value;
		f.prepend(input);
		console.log("ho aggiunto");
	}
	
//	alert("fine");
	
//	f.submit();
});

//var input = document.getElementById("imp-search");

//$('input[name=search]').on('keyup',function(e){
	
//	e.preventDefault();
//	console.log(e.keyCode);
//	e.stopImmediatePropagation();
//	if(event.keyCode  == 13 ){
//		console.log("CHIAMO BTNN");
//		alert("premuto invio");
//		$('.btnn').click();
//	}
//	
//});
//$('#imp-search').on('keyup',function(){
//$('#imp-search').on('keyup',function(event){
//	console.log("dentro");
//	
//	if ( event.which == 13 ) {
//		event.preventDefault();
//		console.log("entrato");
//		$('.btnn').click();
//	}
//});
//input.addEventListener("keyup",function(event){
//	console.log("dentro");
//	
//	if ( event.keyCode  == 13 ) {
//		event.preventDefault();
//		console.log("entrato");
//		$('.btnn').click();
//	}
//});


$('.button').on('click',function(){
	console.log("dentro");
	
	var e = document.getElementById("category-elem");
	var value = e.value;
	var text = e.options[e.selectedIndex].text;
	
	if(value != 0 && text.toUpperCase() != "ALL CATEGORY"){
		
		var f = document.querySelector("#search-form");
		let input = document.createElement("input");
		input.type="hidden";
		input.name="category";
		input.value=value;
		f.prepend(input);
		
	}	
	f.submit();
});





$(".button btn").on("click", function() {

	  var $button = $(this);
	  var oldValue = $button.parent().find("input").val();

	  console.log(oldValue);

	  if ($button.text() == "+") {
		  var newVal = parseFloat(oldValue) + 1;
		} else {
	   // Don't allow decrementing below zero
	    if (oldValue > 0) {
	      var newVal = parseFloat(oldValue) - 1;
	    } else {
	      newVal = 0;
	    }
	  }

	  $button.parent().find("input").val(newVal);

	});

