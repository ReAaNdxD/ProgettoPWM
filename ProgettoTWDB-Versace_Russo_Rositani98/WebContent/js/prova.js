$(document).ready(function() {
	$('select').niceSelect();
});
$('.btnn').on('click', function() {
	console.log("dentro");
});

$('#categoria').on('change', function() {
	var category = document.getElementById("categoria");
	var categoria = document.getElementById("categoria").value;
	var sottocategoria = document.getElementById("sottocategoria");
	console.log(categoria);
	if (categoria != "") {
		document.getElementById("sottocategoria").innerHTML = "";
		var xhttp = new XMLHttpRequest();
		url = "/privato/prodotto/APISottocategoria?category=" + categoria;
		xhttp.open("GET", url, true);
		xhttp.send();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if (xhttp.responseText == "") {
					return;
				}

				var slots = JSON.parse(xhttp.responseText);
				console.log(slots)
				let c = 0;
				for (k in slots) {

					if (c == 0) {
						document.getElementById("sottocategoria").innerHTML += "<option value="
							+ slots[k].idSottocategoria
							+ " selected=\"selected\">"
							+ slots[k].nome
							+ "</option>";
						c = 1;
					} else {
						document.getElementById("sottocategoria").innerHTML += "<option value="
							+ slots[k].idSottocategoria
							+ ">"
							+ slots[k].nome
							+ "</option>";
					}
				}

				$('#categoria').niceSelect('update');

				$('#sottocategoria').niceSelect('update');

			}
		}
	} else {
		document.getElementById("sottocategoria").innerHTML = "";
	}


});$('#sottocategoria').on('change', function() {
	var category = document.getElementById("categoria");
	var categoria = document.getElementById("categoria").value;
	var sottocategoria = document.getElementById("sottocategoria");
	console.log(categoria);
	if (sottocategoria != "") {
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
		var xhttp = new XMLHttpRequest();
		url = "/privato/prodotto/APIProdotto?subcategory=" + sottocategoria;
		xhttp.open("GET", url, true);
		xhttp.send();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if (xhttp.responseText == "") {
					return;
				}

				var slots = JSON.parse(xhttp.responseText);
				console.log(slots)
				/*let c = 0;*/
				for (k in slots) {

					/*if (c == 0) {*/
						document.getElementById("products-list").innerHTML += "<option data-value="
						+ slots[k].idProdotto
						+ ">"
						+ slots[k].nome
						+ "</option>";
				/*	} else {
						document.getElementById("sottocategoria").innerHTML += "<option value="
							+ slots[k].idSottocategoria
							+ ">"
							+ slots[k].nome
							+ "</option>";
					}*/
				}

				$('#categoria').niceSelect('update');

				$('#sottocategoria').niceSelect('update');
				$('#sottocategoria').niceSelect('update');

			}
		}
	} else {
		document.getElementById("sottocategoria").innerHTML = "";
	}


});
