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

/**
 * Servlet implementation class RichiediModificaCartaCredito
 */
@WebServlet("/privato/cliente/cartacredito/RichiediModificaCartaCredito")
public class RichiediModificaCartaCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediModificaCartaCredito() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("idCliente") != null) {
			int idCarta = Integer.parseInt(request.getParameter("id"));
			CartaCreditoDAO ccDAO = new CartaCreditoDAO();
			CartaCredito c = ccDAO.get(new CartaCredito(idCarta));
			session.setAttribute("cartacredito", c);
			request.getRequestDispatcher("modificaCartaCredito.jsp").forward(request, response);
		}else {
			response.sendRedirect("/login.jsp");
		}
	}

}
