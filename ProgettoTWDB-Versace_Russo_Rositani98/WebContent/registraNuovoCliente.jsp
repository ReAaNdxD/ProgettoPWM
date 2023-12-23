<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>
<hr>
		<h4>Registrazione</h4> 	
		<s:form action="RegistraNuovoCliente">
	    	<s:textfield name="cliente.nome" label="Nome"/>
	    	<s:textfield name="cliente.cognome" label="Cognome"/>
<%-- 	    	<s:date name="cliente.dataDiNascita" format="dd/mm/yyyy"/>
 --%>	    	<s:textfield name="cliente.email" label="Email"/>
	    	<s:password name="cliente.password" label="Password"/>
	    	<s:password label="Riscrivi Password"/>
	    	
	    	<s:submit/>
		</s:form>
	    <hr>
</body>
</html>