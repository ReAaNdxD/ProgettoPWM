$("input:radio")
		.on(
				'change',
				function() {
					let tipo = $('input[name="radio"]:checked').val();
					// Dobbiamo fre la chiamata Ajax
					var xhttp = new XMLHttpRequest();
					url = "/privato/cliente/ordine/CalcoloSpedizione";
					xhttp.open("GET", url, true);
					xhttp.send();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							if (xhttp.responseText == "") {
								return;
							}
							var costoParziale = $("#spanu").text();
							console.log(costoParziale+" dfsg")
							var metodo = JSON.parse(xhttp.responseText);
							let costo=0;
							for (k in metodo) {
								if (metodo[k].idMetodoSpedizione == tipo) {
									document.getElementById("spanuccio").innerHTML =  metodo[k].speseSpedizione;
//									costo =+costoParziale+ +metodo[k].speseSpedizione;
									costo =(parseFloat(costoParziale) + parseFloat(metodo[k].speseSpedizione)).toFixed(2);
									console.log(costoParziale)
									console.log(metodo[k].speseSpedizione)
									console.log(costo)
									document.getElementById("spanuccia").innerHTML = costo;
								}
							}
						} else {
							document.getElementById("spanuccio").innerHTML = "0";
						}
					}
				});
