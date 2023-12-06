package it.unirc.db.ecommerce.servlet.privato.cliente.cartacredito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RichiediAggiungiCartaCredito
 */
@WebServlet("/privato/cliente/cartacredito/RichiediAggiungiCartaCredito")
public class RichiediAggiungiCartaCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediAggiungiCartaCredito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		session.setAttribute("idCliente", false);
		if (session.getAttribute("idCliente") != null)
			request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
		else
			response.sendRedirect("/login.jsp");
	}

}
