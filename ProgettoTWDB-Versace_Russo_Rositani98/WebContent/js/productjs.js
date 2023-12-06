

function changeCategory() {
	var categoria = document.getElementById("categoria").value;
	var table1 = document.getElementById("body");
	console.log(table1);
	console.log(categoria);
	if (categoria != "") {
		document.getElementById("sottocategoria").innerHTML = "";
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
		document.getElementById("body").innerHTML = "";
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
				document.getElementById("sottocategoria").innerHTML = "<option></option>"
				for (k in slots) {
					document.getElementById("sottocategoria").innerHTML += "<option value="
						+ slots[k].idSottocategoria
						+ ">"
						+ slots[k].nome
						+ "</option>";
				}
				var xhttps = new XMLHttpRequest();
				urls = "/privato/prodotto/APIProdotto2?category=" + categoria;
				xhttps.open("GET", urls, true);
				xhttps.send();
				xhttps.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						if (xhttps.responseText == "") {
							return;
						}
						var table = JSON.parse(xhttps.responseText);
						console.log(table)
						let i = 1
						for (k in table) {

							document.getElementById("body").innerHTML += "<tr>"
								+ "<th scope='row''>" + i + "</th>"
								+ "<td>" + table[k].nome + "</td>"
								+ "<td> " + table[k].marca + "</td> "
								+ "<td><form action='RichiediModificaProdotto' method='post'>"
								+ "<input type='hidden' name='prodotto' value=" + table[k].idProdotto + ">"
								+ "<button type='submit' class='btn btn-outline-secondary'>Modifica</input>"
								+ "</form></td>"
								+ "<td><a href='RimuoviProdotto?idProdotto=" + table[k].idProdotto + "'><button type='button'' class='btn btn-outline-secondary'>Rimuovi</button></a></td> ";
							document.getElementById("products-list").innerHTML += "<option data-value="
								+ table[k].idProdotto
								+ ">"
								+ table[k].nome
								+ "</option>";

							i++;

						}

					}
				}
			} else {
				document.getElementById("sottocategoria").innerHTML = "";
				document.getElementById("products-list").innerHTML = "";
				document.getElementById("prodotto").value = "";
				document.getElementById("body").innerHTML = "";
			}
		}
	} else {
		document.getElementById("sottocategoria").innerHTML = "";
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
		document.getElementById("body").innerHTML = "";
	}
}
function changeSubcategory() {
	var sottocategoria = document.getElementById("sottocategoria").value;
	var table1 = document.getElementById("body");
	console.log(table1);
	console.log(sottocategoria);
	if (sottocategoria != "") {
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
		document.getElementById("body").innerHTML = "";


		var xhttp = new XMLHttpRequest();
		url = "/privato/prodotto/APIProdotto?subcategory=" + sottocategoria;
		xhttp.open("GET", url, true);
		xhttp.send();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if (xhttp.responseText == "") {
					return;
				}

				var table = JSON.parse(xhttp.responseText);

				console.log(table);
				let i = 1;
				for (k in table) {

					document.getElementById("body").innerHTML += "<tr>"
						+ "<th scope='row''>" + i + "</th>"
						+ "<td>" + table[k].nome + "</td>"
						+ "<td> " + table[k].marca + "</td> "
						+ "<td><form action='RichiediModificaProdotto' method='post'>"
						+ "<input type='hidden' name='prodotto' value=" + table[k].idProdotto + ">"
						+ "<button type='submit' class='btn btn-outline-secondary'>Modifica</input>"
						+ "</form></td>"
						+ "<td><a href='RimuoviProdotto?idProdotto=" + table[k].idProdotto + "'><button type='button'' class='btn btn-outline-secondary'>Rimuovi</button></a></td> ";
					document.getElementById("products-list").innerHTML += "<option data-value="
						+ table[k].idProdotto
						+ ">"
						+ table[k].nome
						+ "</option>";

					i++;

				}

			}
		}
	} else {
		var categoria = document.getElementById("categoria").value;
		var xhttps = new XMLHttpRequest();
		urls = "/privato/prodotto/APIProdotto2?category=" + categoria;
		xhttps.open("GET", urls, true);
		xhttps.send();
		document.getElementById("products-list").innerHTML = "";
		document.getElementById("prodotto").value = "";
		document.getElementById("body").innerHTML = "";
		xhttps.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if (xhttps.responseText == "") {
					return;
				}
				var table = JSON.parse(xhttps.responseText);
				console.log(table)
				let i = 1
				for (k in table) {

					document.getElementById("body").innerHTML += "<tr>"
						+ "<th scope='row''>" + i + "</th>"
						+ "<td>" + table[k].nome + "</td>"
						+ "<td> " + table[k].marca + "</td> "
						+ "<td><form action='RichiediModificaProdotto' method='post'>"
						+ "<input type='hidden' name='prodotto' value=" + table[k].idProdotto + ">"
						+ "<button type='submit' class='btn btn-outline-secondary'>Modifica</input>"
						+ "</form></td>"
						+ "<td><a href='RimuoviProdotto?idProdotto=" + table[k].idProdotto + "'><button type='button'' class='btn btn-outline-secondary'>Rimuovi</button></a></td> ";
					document.getElementById("products-list").innerHTML += "<option data-value="
						+ table[k].idProdotto
						+ ">"
						+ table[k].nome
						+ "</option>";

					i++;

				}

			}
		}

	}
}

function searchProduct() {
	var character = document.getElementById("prodotto").value;
	console.log("lolo " + character);
	document.getElementById("body").innerHTML = "";
	var categoria = document.getElementById("categoria").value;
	var sottocategoria = document.getElementById("sottocategoria").value;
	var xhttp = new XMLHttpRequest();
	if (categoria == "")
		url = "/privato/prodotto/APIGetAllProdotto?character=" + character;
	else if (sottocategoria != "")
		url = "/privato/prodotto/APIProdotto3?subcategory=" + sottocategoria + "&charcater=" + character;

	else if (categoria != "" && sottocategoria == "")
		url = "/privato/prodotto/APIProdotto4?category=" + categoria + "&charcater=" + character;

	xhttp.open("GET", url, true);
	xhttp.send();
	document.getElementById("products-list").innerHTML = "";
	document.getElementById("body").innerHTML = "";
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (xhttp.responseText == "") {
				return;
			}

			var table = JSON.parse(xhttp.responseText);
			let i = 1;
			for (k in table) {

				document.getElementById("body").innerHTML += "<tr>"
					+ "<th scope='row''>" + i + "</th>"
					+ "<td>" + table[k].nome + "</td>"
					+ "<td> " + table[k].marca + "</td> "
					+ "<td><form action='RichiediModificaProdotto' method='post'>"
					+ "<input type='hidden' name='prodotto' value=" + table[k].idProdotto + ">"
					+ "<button type='submit' class='btn btn-outline-secondary'>Modifica</input>"
					+ "</form></td>"
					+ "<td><a href='RimuoviProdotto?idProdotto=" + table[k].idProdotto + "'><button type='button'' class='btn btn-outline-secondary'>Rimuovi</button></a></td> ";
				document.getElementById("products-list").innerHTML += "<option data-value="
					+ table[k].idProdotto
					+ ">"
					+ table[k].nome
					+ "</option>";

				i++;

			}

		} else {
			document.getElementById("products-list").innerHTML = "";
			document.getElementById("body").innerHTML = "";
		}
	}
}




