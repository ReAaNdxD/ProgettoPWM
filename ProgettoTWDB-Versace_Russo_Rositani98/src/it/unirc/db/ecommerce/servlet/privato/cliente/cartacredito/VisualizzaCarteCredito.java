package it.unirc.db.ecommerce.servlet.privato.cliente.cartacredito;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.CartaCredito;
import it.unirc.db.ecommerce.beans.CartaCreditoDAO;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClienteDAO;

/**
 * Servlet implementation class VisualizzaCarteCredito
 */
@WebServlet(description = "visualizza le carte di credito di un cliente", urlPatterns = {
		"/privato/cliente/cartacredito/VisualizzaCarteCredito" })
public class VisualizzaCarteCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaCarteCredito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("idCliente") != null) {
			int idCliente = (int) session.getAttribute("idCliente");
			Cliente c = new Cliente(idCliente);
			CartaCreditoDAO ccDAO = new CartaCreditoDAO();
			Vector<CartaCredito> carte = ccDAO.getAllByCliente(c);

			request.setAttribute("carte", carte);

//		passiamo la palla a una jsp 
			request.getRequestDispatcher("visualizzaCarteCredito.jsp").forward(request, response);
		} else {
			response.sendRedirect("/login.jsp");
		}

	}

}
