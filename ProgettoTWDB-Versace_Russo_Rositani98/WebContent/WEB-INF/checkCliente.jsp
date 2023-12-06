
<%
	if (session.getAttribute("idCliente") == null) {
		response.sendRedirect("/SigninCliente");
		return;
	}
%>

