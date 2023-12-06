function conta() {
	var numCar = document.getElementById("area").value.length;
	document.getElementById("messaggio").innerHTML = "Numero caratteri restanti: "
		+ (45 - numCar);
}
function conta2() {
	var numCar = document.getElementById("area2").value.length;
	document.getElementById("messaggio2").innerHTML = "Numero caratteri restanti: "
		+ (140 - numCar);
}
var validNumber = new RegExp(/^\d*\.?\d*$/);
var lastValid = document.getElementById("test1").value;
function validateNumber(elem) {
	if (validNumber.test(elem.value)) {
		lastValid = elem.value;
	} else {
		elem.value = lastValid;
	}
}
function isNumberKey(txt, evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode == 46) {
		//Check if the text already contains the . character
		if (txt.value.indexOf('.') === -1) {
			return true;
		} else {
			return false;
		}
	} else {
		if (charCode > 31 &&
			(charCode < 48 || charCode > 57))
			return false;
	}
	return true;
}
function changeSottocategory() {
	var categoria = document.getElementById("categoria").value;

	console.log(categoria);
	if (categoria != "") {
		document.getElementById("sottocategoria").innerHTML = "";
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
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
				document.getElementById("sottocategoria").innerHTML = "<option></option>";
				for (k in slots) {
					document.getElementById("sottocategoria").innerHTML += "<option value="
						+ slots[k].idSottocategoria
						+ ">"
						+ slots[k].nome
						+ "</option>";
				}

				// Oppure
				/*
				 * slots.forEach(element => {
				 * document.getElementById("sottocategoria").innerHTML += "<option
				 * value="+element.idSottocategoria+">"+element.nome+"</option>";
				 * });
				 */

			}

		}
	} else {
		document.getElementById("sottocategoria").innerHTML = "";
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
	}
}
function changeSottocategoria() {
	var categoria = document.getElementById("categoria").value;

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
				for (k in slots) {
					document.getElementById("sottocategoria").innerHTML += "<option value="
						+ slots[k].idSottocategoria
						+ ">"
						+ slots[k].nome
						+ "</option>";
				}
			}
		}
	} else {
		document.getElementById("sottocategoria").innerHTML = "";
	}
	
}

function changeProduct() {
	var sottocategoria = document.getElementById("sottocategoria").value;

	console.log(sottocategoria);
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
				for (k in slots) {
					document.getElementById("products-list").innerHTML += "<option data-value="
						+ slots[k].idProdotto
						+ ">"
						+ slots[k].nome
						+ "</option>";
				}

				// Oppure
				/*
				 * slots.forEach(element => {
				 * document.getElementById("sottocategoria").innerHTML += "<option
				 * value="+element.idSottocategoria+">"+element.nome+"</option>";
				 * });
				 */

			}

		}
	} else {
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";

	}
	 
}

function onlyNumbers(evt) {
    // It's called the ternary conditional operator.
    // It's basically short for an if...else
    // Si può tradurre come riportato sotto
    // var charCode;
    // if(evt.which) {
    // charCode = evt.which;
    // }
    // else {
    // charCode = evt.keyCode;
    // }
    // evt.which ritorna il codice numerico del tasto
    // che è stato premuto, sia esso alfanumerico oppure no.
    // Essendo però che non è compatibile con i vecchi browser uso anche keyCode

    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) {
        return false;
    }
    return true;
}
