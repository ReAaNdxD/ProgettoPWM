package it.unirc.db.ecommerce.servlet.privato.cliente.cartacredito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.CartaCredito;
import it.unirc.db.ecommerce.beans.CartaCreditoDAO;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.join.ClientePossiedeCartaDAO;

/**
 * Servlet implementation class RimuoviCartaCredito
 */
@WebServlet("/privato/cliente/cartacredito/RimuoviCartaCredito")
public class RimuoviCartaCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimuoviCartaCredito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCarta = Integer.parseInt(request.getParameter("id"));

		CartaCredito c = new CartaCredito();
		c.setIdCartaCredito(idCarta);
		CartaCreditoDAO cDAO = new CartaCreditoDAO();
		c = cDAO.get(c);

		HttpSession session = request.getSession();
		int idCliente = (int) session.getAttribute("idCliente");
		Cliente cliente = new Cliente(idCliente);
		// prima elimino la tupla nella join
		ClientePossiedeCartaDAO possiedeDAO = new ClientePossiedeCartaDAO();
		if (possiedeDAO.elimina(cliente, c)) {
			cDAO.elimina(c);
			// Se restituisce false significa che un altro cliente sta usando la stessa
			// carta di credito...comunque:
			response.sendRedirect("/privato/cliente/cartacredito/VisualizzaCarteCredito");
		}
//			else {
//			
//		}
//		 else???
	}

}
