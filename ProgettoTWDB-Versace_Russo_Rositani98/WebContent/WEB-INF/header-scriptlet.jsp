<%
		CategoriaDAO categoriaDAO = new CategoriaDAO();
	    ArticoloDAO articoloDAO = new ArticoloDAO();
	//  ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
	//  CarrelloDAO carrelloDAO = new CarrelloDAO();
// 	    Cliente cliente = new Cliente(2);
		Cliente cliente=null;
	    if(session.getAttribute("idCliente")!=null)
	    	cliente = new Cliente((int) session.getAttribute("idCliente"));// == null
	    Vector<ViewProduct> articoliCarrello=null;
	    if(cliente!=null){
	    	articoliCarrello = articoloDAO.getAllArticoliCliente(cliente);
	    }
	//  Carrello carrello=null;
	//  if(session.getAttribute("cliente")!=null){
	//  	carrello = (Carrello) session.getAttribute("cliente");
	//  }
// 	System.out.println("cliente "+cliente);
// 	System.out.println("articoli "+articoliCarrello);
		int item_nav = 0;
		Integer category_url=null;
		if(request.getParameter("category")!=null)
			category_url = Integer.parseInt(request.getParameter("category"));
		String ref = (String) request.getParameter("ref");
%>