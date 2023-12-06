function alphaOnly(event) {
	var ASCIICode = (event.which) ? event.which : event.keyCode
	if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) {
		return true;
	}
	return false;
}
function ValidateEmail(mail) {
	if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
			.test(mail)) {
		return true;
	}
	alert("You have entered an invalid email address!");
	return false;
}
function CheckPassword(inputtxt) {
	// To check a password between 8 to 15 characters which contain at least one
	// lowercase letter,
	// one uppercase letter, one numeric digit, and one special character
	var paswd = /^(?=.*[0-9])(?=.*[!@#$%^&*.])[a-zA-Z0-9!@#$%^&*.]{7,15}$/;
	if (inputtxt.value.match(paswd)) {
		alert('Correct');
		return true;
	} else {
		alert('Wrong...!')
		return false;
	}
}
function valida() {
	const nome = document.getElementById("nome");
	const cognome = document.getElementById("cognome");
	const dataNascita = document.getElementById("dataNascita");
	const email = document.getElementById("email");
	const password = document.getElementById("password");
	const verificaPassword = document.getElementById("verificaPassword");
	let valida = true;
	let cont = true;
	var regex = /^[a-zA-Z]+$/;
	if (nome.value.replace(/\s+/, "") == "") {
		cont = setError(nome, "Inserisci il nome");
		alert("Dentro 1if");
		valida = valida && cont;
	} else if (!regex.test(nome.value.replace(/\s+/, ""))) {
		// Se contiene numeri
		cont = setError(nome, "Nome deve contenere solo lettere");
		alert("Dentro 1elseif");
		valida = valida && cont;
	} else {
		cont = setSuccess(nome);
		valida = valida && cont;
		alert("Dentro 1else");
	}
	if (cognome.value.replace(/ /g, '') == "") {
		// Elimina ogni spazio => replace(/ /g, '')
		cont = setError(cognome, "Inserisci il cognome");
		valida = valida && cont;
		alert("Dentro 2if");
	} else {
		if (!regex.test(cognome.value.replace(/ /g, ''))) {
			// Se contiene numeri
			cont = setError(cognome, "Cognome deve contenere solo lettere");
			valida = valida && cont;
		} else {
			cont = setSuccess(cognome);
			valida = valida && cont;
		}
	}
	if (dataNascita.value.replace(/ /g, '') == "")
		cont = setError(dataNascita, "Inserisci la tua data di nascita");
	else {
		cont = setSuccess(dataNascita);
	}
	valida = valida && cont;
	if (email.value.replace(/ /g, '') == "")
		cont = setError(email, "Inserisci un email");
	else {
		cont = ValidateEmail(email.value) ? setSuccess(email) : setError(email,
				"L'email inserita non e' valida");
	}
	valida = valida && cont;
	if (password.value.replace(/ /g, '') == "")
		cont = setError(password, "Inserisci una password");
	else {
		cont = CheckPassword(password) ? setSuccess(password)
				: setError(
						password,
						"La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale");
	}
	valida = valida && cont;
	if (verificaPassword.value.replace(/ /g, '') == "")
		cont = setError(verificaPassword, "Inserisci nuovamente la password");
	else {
		if (password.value != verificaPassword.value)
			cont = setError(verificaPassword, "Le password non coincidono");
		else
			cont = setSuccess(verificaPassword);
	}
	valida = valida && cont;
	return valida;
}

function validaLogin() {
	const email = document.getElementById("email");
	const password = document.getElementById("password");
	let valida = true;
	let cont = true;
	if (email.value.replace(/ /g, '') == "")
		cont = setError(email, "Inserisci un email");
	else {
		cont = ValidateEmail(email.value) ? setSuccess(email) : setError(email,
				"L'email inserita non e' valida");
	}
	valida = valida && cont;
	if (password.value.replace(/ /g, '') == "")
		cont = setError(password, "Inserisci una password");
	else {
		cont = CheckPassword(password) ? setSuccess(password)
				: setError(
						password,
						"La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale");
	}
	valida = valida && cont;
	return valida;
}

function setError(input, m) {
	input.className = "error"
	const ciao = input.nextElementSibling;

	ciao.className = "error"
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
