package it.unirc.db.ecommerce.servlet.privato.venditore;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;

/**
 * Servlet implementation class ModificaVenditore
 */
@WebServlet("/privato/venditore/ModificaVenditore")
public class ModificaVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaVenditore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idVenditore = 0;
		HttpSession session = request.getSession();
		if (session.getAttribute("Venditore") != null) {

			idVenditore = ((Venditore) session.getAttribute("Venditore")).getIdVenditore();

		} else if (session.getAttribute("Admin") != null) {
			idVenditore = 1;
		} else {
			response.sendRedirect("/LoginVenditore");
			return;
		}
		VenditoreDAO venditoreDAO = new VenditoreDAO();
		String email = request.getParameter("email");
		String emailReal = venditoreDAO.get(new Venditore(idVenditore)).getEmail();
		System.out.println(emailReal);
		if (!email.equals(emailReal)) {
			request.setAttribute("erroreEmail", true);
			request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
			return;

		}
		String password = request.getParameter("password");
		if ((password.trim().equals("")) && (password.length() > 45)) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
			return;
		}
		String nomeAzienda = request.getParameter("nomeAzienda");
		if ((nomeAzienda.trim().equals("")) && (nomeAzienda.length() > 45)) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
			return;

		}
		BigDecimal pIVA = null;
		try {
			pIVA = new BigDecimal(request.getParameter("pIVA"));
			if (pIVA.toString().length() != 11 || pIVA.compareTo(pIVA) < 0) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
			return;
		}

		Venditore venditore = new Venditore(idVenditore, password, nomeAzienda, pIVA);

		if (venditoreDAO.modifica(venditore)) {
			if (session.getAttribute("Venditore")!=null) {
				
				session.setAttribute("Venditore", venditoreDAO.get((Venditore) session.getAttribute("Venditore")));
			}
			response.sendRedirect("/privato/venditore/VisualizzaVenditore");
		} else {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
			return;
		}
	}

}
