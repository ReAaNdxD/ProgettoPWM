package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.beans.ProdottoDAO;
import it.unirc.db.ecommerce.beans.Venditore;

/**
 * Servlet implementation class ModificaProdottoInVendita
 */
@WebServlet("/privato/prodotto/ModificaProdottoInVendita")
public class ModificaProdottoInVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProdottoInVendita() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Articolo a = (Articolo) session.getAttribute("Articolo");
		session.setAttribute("Articolo", null);
		ArticoloDAO aDAO = new ArticoloDAO();
		float prezzo;
		double price = 0;
		if (session.getAttribute("Venditore") != null) {
			Venditore venditore = (Venditore) session.getAttribute("Venditore");
			if (venditore.getIdVenditore() != a.getIdVenditore()) {
				response.sendRedirect("VisualizzaProdottiInVendita");
				return;
			}

		} else if (session.getAttribute("Admin") != null && (a.getIdVenditore() != 1)) {
			response.sendRedirect("VisualizzaProdottiInVendita");
			return;

		} else if((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			response.sendRedirect("/RichiediLoginVenditore");
			return;

		}

		if ((request.getParameter("prezzo") != null) && (!request.getParameter("prezzo").trim().equals(""))) {
			try {
				price = Double.parseDouble(request.getParameter("prezzo"));

			} catch (NumberFormatException e) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaProdottoInVendita.jsp").forward(request, response);
				return;
			}
		}

		prezzo = BigDecimal.valueOf(price).setScale(2, RoundingMode.DOWN).floatValue();
		a.setPrezzo(prezzo);

		if (aDAO.modifica(a)) {
			System.out.println("modifica");
			request.getRequestDispatcher("VisualizzaProdottiInVendita").forward(request, response);
			return;
		} else {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaProdottoInVendita.jsp").forward(request, response);
			return;

		}
	}

}
