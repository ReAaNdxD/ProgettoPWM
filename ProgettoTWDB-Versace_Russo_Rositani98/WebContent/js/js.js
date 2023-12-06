//$(".button").on("click", function() {
//
//  var $button = $(this);
//  var oldValue = $button.parent().find("input").val();
//
//  console.log(oldValue);
//
//  if ($button.text() == "+") {
//	  var newVal = parseFloat(oldValue) + 1;
//	} else {
//   // Don't allow decrementing below zero
//    if (oldValue > 0) {
//      var newVal = parseFloat(oldValue) - 1;
//    } else {
//      newVal = 0;
//    }
//  }
//
//  $button.parent().find("input").val(newVal);
//
//});



//$(".button_container").on("click", function() {
//
//	  var $button = $(this);
//	  var qty = $button.parent().find(".input-number").val();
//	  var id = $('#idArticle').val();
//	  console.log(qty);
//	  console.log(id);
//	  
//	  $.get('/AddToCart', {
//		  "id" : id,
//		  "qty" : qty
//	  }, function(responseText) {
//		  if(responseText == "Aggiunto con successo"){
//			  $('#message').addClass("alert alert-success alert-dismissible");
//		  }else {
//			  $('#message').addClass("alert alert-danger alert-dismissible");
//		  }
//	      $('#message').text(responseText);
//	  });
	  
//	  var xhttp = new XMLHttpRequest();
//		url = "/AddToCart?id=36&qty=2";
//		xhttp.open("GET", url, true);
//		xhttp.send();
//		xhttp.onreadystatechange = function() {
//			if (this.readyState == 4 && this.status == 200) {
//				if (xhttp.responseText == "") {
//					console.log("vuoto");
//					return;
//				}
//			}
//		}
//		
//		if (xhttp.responseText == "")
//			xhttp.responseText="esfonesf";
//		
//		console.log(xhttp.responseText);
//		var value = xhttp.responseText;
//		var f = document.querySelector("form");
//		
//		var p = document.createElement("p");
//		  // ed assegnargli un contenuto
//		  var newContent = document.createTextNode(value);
//		  // aggiungi il nodo di testo al div appena creato
//		  p.appendChild(newContent);
//		f.prepend(p);
	
//	  if ($button.text() == "+") {
//		  var newVal = parseFloat(oldValue) + 1;
//		} else {
//	   // Don't allow decrementing below zero
//	    if (oldValue > 0) {
//	      var newVal = parseFloat(oldValue) - 1;
//	    } else {
//	      newVal = 0;
//	    }
//	  }
//
//	  $button.parent().find("input").val(newVal);

//	});



//$(document).ready(function() {
//    $('#userName').blur(function(event) {
//            var name = $('#userName').val();
//            $.get('GetUserServlet', {
//                    userName : name
//            }, function(responseText) {
//                    $('#ajaxGetUserServletResponse').text(responseText);
//            });
//    });
//});




//$(".action.remove").on("click", function() {
//	console.log("dentro");
////	var id = $('#idArticle').val();
//	var $button = $(this);
//	var id = $button.parent().find("input[type=hidden]").val();
//	
//	console.log(id);
////	  var $button = $(this);
////	  var qty = $button.parent().find(".input-number").val();
////	  var id = $('#idArticle').val();
////	  console.log(qty);
////	  console.log(id);
////	  
//	  $.get('/RemoveArticle', {
//		  "id" : id,
//	  }, function(responseText) {
////		  var json = JSON.stringify(responseText);
//		  
//		  var $ul = $("<ul>").appendTo($("#message")); // Create HTML <ul> element and append it to HTML DOM element with ID "somediv".
//	        $.each(responseText, function(index, item) { // Iterate over the JSON array.
//	        	
////	        	var item2 = JSON.parse(JSON.stringify(item.nomeProdotto));
//	        	
//	            $("<li>").text(JSON.parse(JSON.stringify(item.nomeProdotto))).appendTo($ul);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>.
//	        });
//	        
////	        for (var i = 0; i < responseText.length; ++i) {
////                console.log(responseText[i].nomeProdotto);
////                $('#message').text(JSON.stringify(responseText[i].nomeProdotto));
////            }
////		  console.log("dentro");
////		  console.log("Json "+JSON.stringify(responseText[i]));
////		  conosle.log("Response "+responseText);
////		  if(responseText == "Rimosso con successo"){
////			  $('#message').addClass("alert alert-success alert-dismissible");
////		  }else {
////			  $('#message').addClass("alert alert-danger alert-dismissible");
////		  }
////	      $('#message').text(json);
//	  });
//
//	});

$('.button').on('click',function(){
	console.log("dentro");
	
	var $button = $(this);
	var value = $button.parent().find(".input-number").val();
	var value1 = $button.parent().find(".btn.btn-primary.btn-number");
	var id = value1.data("id");
//	var id = $button.data("id");	
	console.log(id);
	var f = document.querySelector("#inc-btn");
	let input1 = document.createElement("input");
	let input2 = document.createElement("input");
	input1.type="hidden";
	input1.name="qty";
	input1.value=value;
	f.prepend(input1);
	
	input2.type="hidden";
	input2.name="id";
	input2.value=id;
	
	f.append(input2);
	
		
	f.submit();
		
});


$('.input-number').on('change',function(){
	console.log("dentro");
	
	var $button = $(this);
	var value = $button.parent().find(".input-number").val();
	var value1 = $button.parent().find(".btn.btn-primary.btn-number");
	var id = value1.data("id");
//	var id = $button.data("id");	
	console.log(id);
	var f = document.querySelector("#inc-btn");
	let input1 = document.createElement("input");
	let input2 = document.createElement("input");
	input1.type="hidden";
	input1.name="qty";
	input1.value=value;
	f.prepend(input1);
	
	input2.type="hidden";
	input2.name="id";
	input2.value=id;
	
	f.append(input2);
	
		
	f.submit();
		
});


