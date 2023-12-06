package it.unirc.db.ecommerce.servlet.privato.cliente.carrello;

import java.io.IOException;

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
 * Servlet implementation class RemoveArticle
 */
@WebServlet("/privato/cliente/carrello/RemoveArticle")
public class RemoveArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveArticle() {
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
		
		ArticoloDAO articoloDAO = new ArticoloDAO();
		//input
		Integer idArticolo=null;
		if (request.getParameter("id")!=null) {
			System.out.println("ID Diverso Da NULL");
			idArticolo = Integer.parseInt(request.getParameter("id"));
		}
		
		Articolo articolo = articoloDAO.get(new Articolo(idArticolo));
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Boolean success;
		String msg="";
		Cliente cliente = new Cliente((int) session.getAttribute("idCliente"));
		Carrello carrello = carrelloDAO.getCarrelloByCliente(cliente);
		ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
		if (componeDAO.elimina(articolo, carrello)) {
			
			msg = "Rimosso con successo";
			success = true;
			session.setAttribute("msg",	msg);
			session.setAttribute("success",	success);
		}else {
			msg = "Impossibile Rimuovere";
			success = false;
			session.setAttribute("msg",	msg);
			session.setAttribute("success",	success);
		}
		response.sendRedirect("/privato/cliente/carrello/Cart");
	}

}
