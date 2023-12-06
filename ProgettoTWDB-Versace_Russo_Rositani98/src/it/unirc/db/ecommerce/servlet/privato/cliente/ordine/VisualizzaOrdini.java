package it.unirc.db.ecommerce.servlet.privato.cliente.ordine;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.OrdineDAO;
import it.unirc.db.ecommerce.views.Order;

/**
 * Servlet implementation class VisualizzaOrdini
 */
@WebServlet("/privato/cliente/ordine/VisualizzaOrdini")
public class VisualizzaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaOrdini() {
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
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		int idCliente = (int) session.getAttribute("idCliente");
		OrdineDAO oDAO = new OrdineDAO();
		Vector<Order> ordini = oDAO.getAllOrdini(new Cliente(idCliente));
		System.out.println(ordini);
		request.setAttribute("ordini", ordini);
		request.getRequestDispatcher("/WEB-INF/privato/cliente/ordine/visualizzaOrdini.jsp").forward(request, response);
	}

}
