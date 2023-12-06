package it.unirc.db.ecommerce.servlet.privato.cliente.carrello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.beans.Carrello;
import it.unirc.db.ecommerce.beans.CarrelloDAO;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		
		Cliente cliente = new Cliente((int) session.getAttribute("idCliente"));
		
		ArticoloDAO articoloDAO = new ArticoloDAO();
		//input
		Integer idArticolo = Integer.parseInt(request.getParameter("id"));
		Boolean success;
		Articolo articolo = articoloDAO.get(new Articolo(idArticolo));
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		String msg="";
		Carrello carrello = carrelloDAO.getCarrelloByCliente(cliente);
		ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
		
		if (componeDAO.aggiungiArticoloCarrello(articolo, carrello, qty)) {
			
			msg = "Aggiunto con successo";
			success = true;
			session.setAttribute("msg",	msg);
			session.setAttribute("success",	success);
//			request.setAttribute("msg",	msg);
//			response.sendRedirect(urlString+"?id="+articolo);
			
		}else {
			msg = "Impossibile Aggiungere";
			success = false;
			session.setAttribute("msg",	msg);
			session.setAttribute("success",	success);
		}
		response.sendRedirect("/privato/cliente/carrello/Cart");
				
//		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
//	    response.setCharacterEncoding("UTF-8");
//	    PrintWriter pw = response.getWriter();// You want world domination, huh?
//	    pw.write(msg);  
//	    System.out.println("OutpustStream"+response.getOutputStream());
	    System.out.println(msg);// Write response body.
//		pw.close();
//		response.setContentType("text/html");
//		PrintWriter pw = response.getWriter();
//		pw.print("Provanfk");
//		pw.close();

	}

}
