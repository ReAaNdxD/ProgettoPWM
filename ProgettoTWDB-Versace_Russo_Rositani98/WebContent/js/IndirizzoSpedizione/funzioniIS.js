const regione = document.getElementById("regione");
const provincia = document.getElementById("provincia");
const citta = document.getElementById("citta");
const via = document.getElementById("via");
const numeroCivico = document.getElementById("numeroCivico");
const cap = document.getElementById("cap");
const telefono = document.getElementById("numeroTelefonico");

function valida() {
    let valida = true;
    let cont = true;
    var regex = /^[a-zA-Z]+$/;
    if (regione.value.replace(/\s+/, "") == "") {
        // Faccio anche qui il controllo che non sia vuoto nel caso in cui mi
        // mette semplicemente uno spazio vuoto
        cont = setError(regione, "Devi inserire una regione");
        valida = valida && cont;
    } else if (!regex.test(regione.value.replace(/\s+/, ""))) {
        // Se contiene numeri
        cont = setError(regione, "Regione deve contenere solo lettere");
        valida = valida && cont;
    } else {
        cont = setSuccess(regione);
        valida = valida && cont;
    }
    if (provincia.value.replace(/ /g, '') == "") {
        // Elimina ogni spazio => replace(/ /g, '')
        cont = setError(provincia, "Devi inserire una provincia");
        valida = valida && cont;
    } else {
        if (!regex.test(provincia.value.replace(/ /g, ''))) {
            // Se contiene numeri
            cont = setError(provincia, "Provincia deve contenere solo lettere");
            valida = valida && cont;
        } else {
            cont = setSuccess(provincia);
            valida = valida && cont;
        }
    }
    cont = (!regex.test(provincia.value.replace(/ /g, ''))) ? setError(
            provincia, "Provincia deve contenere solo lettere") :
        setSuccess(provincia);
    valida = valida && cont;
    if (citta.value.replace(/ /g, '') == "")
        cont = setError(citta, "Devi inserire una citta");
    else {
        cont = !regex.test(citta.value.replace(/ /g, '')) ? setError(citta,
            "Città deve avere solo lettere") : setSuccess(citta);
    }
    valida = valida && cont;
    if (via.value.replace(/ /g, '') == "")
        cont = setError(via, "Devi inserire una via");
    else {
        cont = !regex.test(via.value.replace(/ /g, '')) ? setError(via,
            "Via deve contenere solo lettere") : setSuccess(via);
    }
    valida = valida && cont;
    // numero civico deve contenere sia stringhe che lettere quindi non devo
    // fare il controllo
    cont = numeroCivico.value.replace(/ /g, '') == "" ? setError(numeroCivico,
        "Devi inserire un numero civico") : setSuccess(numeroCivico);
    valida = valida && cont;
    if (cap.value.replace(/ /g, '') == "")
        cont = setError(cap, "Devi inserire un cap");
    else {
        cont = regex.test(cap.value.replace(/ /g, '')) ? setError(cap,
            "Cap deve contenere solo numeri") : setSuccess(cap);
    }
    valida = valida && cont;
    if (telefono.value.replace(/ /g, '') == "")
        cont = setError(telefono, "Devi inserire un telefono");
    else {
        cont = regex.test(telefono.value.replace(/ /g, '')) ? setError(
                telefono, "Telefono deve contenere solo numeri") :
            setSuccess(telefono);
    }
    valida = valida && cont;
    return valida;
}

function setError(input, m) {
    input.className = "error";
    const ciao = input.nextElementSibling;
    ciao.className = "error";
    ciao.innerText = m;
    alert("Dentro Set Error");
    return false;
}

function setSuccess(input) {
    input.className = 'success';
    // hide the error message
    const error = input.nextElementSibling;
    error.innerText = '';
    alert("Dentro Set Success");
    return true;
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

function alphaOnly(event) {
    var ASCIICode = (event.which) ? event.which : event.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) {
        return true;
    }
    return false;
}

function confermaRimozione() {
    return window.confirm("Vuoi cancellare questo indirizzo?");
}

function confermaModifica() {
    return window.confirm("Vuoi modificare questo indirizzo?");
}