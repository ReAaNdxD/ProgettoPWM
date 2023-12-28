<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
    <title>Login</title>
	</head>

	<body>
	    <hr>
		<h4>Login</h4> 	
		<s:form action="Login">
	    	<s:textfield name="cliente.email" label="email"/>
	    	<s:password name="cliente.password" label="Password"/>
	    	<s:submit/>
		</s:form>
		<a href="<s:url action='NuovoCliente'/>">Sei un nuovo Cliente? Registrati</a> 
	    <hr>	
	</body>
	
</html>
