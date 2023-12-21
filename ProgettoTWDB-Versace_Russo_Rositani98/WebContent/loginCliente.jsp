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
	    	<s:textfield name="user.email" label="email"/>
	    	<s:password name="user.password" label="Password"/>
	    	<s:submit/>
		</s:form>
		<s:a action="NuovoCliente">Sei nuovo su DAG?</s:a>
	    <hr>	
	</body>
	
</html>
