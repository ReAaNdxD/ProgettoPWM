<%if((session.getAttribute("Venditore")==null)&&(session.getAttribute("Admin")==null)){
	System.out.println("check");
	response.sendRedirect("/RichiediLoginVenditore");
	return;
}
String ref=(String)request.getParameter("ref");
	%>
	