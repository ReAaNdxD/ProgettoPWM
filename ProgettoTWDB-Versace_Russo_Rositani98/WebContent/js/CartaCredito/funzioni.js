
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
function selectValidation() {

	var categoria = document.getElementById('firts-input').value();
	if ($('#firts-input').val() === '') {

		return null;

	}

// var sottocategoria=

}
// MI creo il listener per il click del form di aggiungiCartaCredito.jsp
const form = document.getElementById("form");
form.addEventListener('submit', (e) => {
	let valid = true;
	valid = validaCartaCredito();
	if (!valid) {
		e.preventDefault();
	}
});


function validaCartaCredito() {
	// Vogliamo sapere il campo nome
	var numCarta = document.getElementById("numeroCarta");
	const numCartaVal = numCarta.value;
	var intestatario = document.getElementById("intestatario");
	const intestatarioVal = intestatario.value;
	var dataScadenza = document.getElementById("dataScadenza");
	const dataScadenzaVal = dataScadenza.value;
	var codiceSicurezza = document.getElementById("codiceSicurezza");
    const codiceSicurezzaVal = codiceSicurezza.value;
	let valida = true;
	let cont = true;
	if(numCartaVal.trim()==""){
		cont = setError(numCarta, "Devi inserire un numero carta");
		valida = valida && cont;
	}
	else{
		// Altri check di numCarta
	cont = numCartaVal.length < 13 || numCartaVal.length>16 ? setError(numCarta, "Numero carta deve essere almeno di 13 cifre") : setSuccess(numCarta);
	valida = valida && cont;
	}	
	cont = intestatarioVal.trim()=="" ? setError(intestatario, "Intestatario vuoto") : setSuccess(intestatario);
	valida = valida && cont;
	cont = dataScadenzaVal.trim()=="" ? setError(dataScadenza, "Impostare una data di scadenza") : setSuccess(dataScadenza);
	valida = valida && cont;
	cont = codiceSicurezzaVal.trim().length != 3 ? setError(codiceSicurezza, "Impostare un codice di sicurezza di 3 cifre") : setSuccess(codiceSicurezza);
	valida = valida && cont;
	return valida;
}

function setError(input, m) {
	input.className = "error"
	const ciao = input.nextElementSibling;
	 ciao.className = "error";
	ciao.innerText = m;
	return false;
}
function setSuccess(input) {
	input.className = 'success';
	// hide the error message
	const error = input.nextElementSibling;
	error.innerText = '';
	return true;
}
function confermaModifica(){
	return confirm("Sicuro di voler modificare?");
	}
function alphaOnly(event){
	var ASCIICode = (event.which) ? event.which : event.keyCode
			if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) {
				return true;
			}
	return false;	
}


/**
 * 
 * function changeSottocategory(){
   var categoria = document.getElementById("categoria").value;
      document.getElementById("sottocategoria").innerHTML="";
   console.log(categoria);
   if(categoria!=""){
    
    var xhttp = new XMLHttpRequest();
    url="/privato/prodotto/APISottocategoria?category="+categoria;
    xhttp.open("GET",url,true );
    xhttp.send();
    xhttp.onreadystatechange= function (){
     if(this.readyState==4 && this.status==200){
      if(xhttp.responseText==""){
       return;
      }

      var slots= JSON.parse(xhttp.responseText);
      console.log(slots)
      for(k in slots) {
       document.getElementById("sottocategoria").innerHTML += "<option value="+slots[k].idSottocategoria+">"+slots[k].nome+"</option>";
      } 

       }

        }
       }
 }
 * 
 */
