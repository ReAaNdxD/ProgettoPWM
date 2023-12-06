package it.unirc.db.ecommerce.servlet.privato.cliente.ordine;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Ordine;
import it.unirc.db.ecommerce.beans.OrdineDAO;
import it.unirc.db.ecommerce.views.ViewOrderProducts;

/**
 * Servlet implementation class VisualizzaArticoliOrdine
 */
@WebServlet("/privato/cliente/ordine/VisualizzaArticoliOrdine")
public class VisualizzaArticoliOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaArticoliOrdine() {
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
		if (request.getParameter("ord") == null) {
			response.sendRedirect("/privato/cliente/ordine/VisualizzaOrdini");
			return;
		}
		int numOrd = Integer.parseInt(request.getParameter("ord"));
		OrdineDAO oDAO = new OrdineDAO();
		Vector<ViewOrderProducts> prodotti = oDAO.prodottiOrdine(new Ordine(numOrd));
		request.setAttribute("prodotti", prodotti);
		request.getRequestDispatcher("/WEB-INF/privato/cliente/ordine/visualizzaArticoliOrdine.jsp").forward(request,
				response);
	}
}
