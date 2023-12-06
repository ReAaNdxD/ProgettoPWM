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
 * Servlet implementation class AddRemove
 */
@WebServlet("/privato/cliente/carrello/AddRemove")
public class AddRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
		
		HttpSession session = request.getSession();
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		ArticoloDAO articoloDAO = new ArticoloDAO();
//		if(session.getAttribute("user")!=null) {
//			Cliente cliente = (Cliente) session.getAttribute("user");
//		}
		Articolo articolo=null;
		if(request.getParameter("id")!=null) {
			int idArticolo = Integer.parseInt(request.getParameter("id"));
			articolo = articoloDAO.get(new Articolo(idArticolo));
		}
		Integer quantita=null;
		if(request.getParameter("qty")!=null) {
			quantita = Integer.parseInt(request.getParameter("qty"));
		}
			
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		
//		Cliente cliente = new Cliente(2);
		Cliente cliente = new Cliente((int) session.getAttribute("idCliente"));
		Carrello cart = carrelloDAO.getCarrelloByCliente(cliente);
//		Carrello carrello = new Carrello(viewProducts.elementAt(table.getSelectedRow()).getIdCarrello());
//		Articolo articolo = new Articolo(viewProducts.elementAt(table.getSelectedRow()).getIdArticolo());
//		int quantita = Integer.parseInt(textField_Quantita.getText());

		if (articolo !=null && cart != null && quantita != null) {
			if (quantita >= 0) {
				if (quantita == 0) {
					componeDAO.elimina(articolo, cart);
				} else {
					componeDAO.modifica(articolo, cart, quantita);
				}
			}
			
			
		}
		
		response.sendRedirect("/privato/cliente/carrello/Cart");
	}

}
