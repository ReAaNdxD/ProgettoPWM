package it.unirc.db.ecommerce.servlet.privato.venditore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;

/**
 * Servlet implementation class VisualizzaVenditore
 */
@WebServlet("/privato/venditore/VisualizzaVenditore")
public class VisualizzaVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaVenditore() {
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
		VenditoreDAO venditoreDAO = new VenditoreDAO();
		if(session.getAttribute("Prodotto")!=null) {
			session.setAttribute("Prodotto",null);
		}
		Venditore venditore = venditoreDAO.get(new Venditore(1));
		if (session.getAttribute("Admin") != null) {
			request.setAttribute("seller", venditore);
		}
		if ((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}

		request.getRequestDispatcher("/privato/venditore/visualizzaVenditore.jsp").forward(request, response);
		return;
	}

}
