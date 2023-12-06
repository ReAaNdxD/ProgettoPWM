package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Categoria;
import it.unirc.db.ecommerce.beans.CategoriaDAO;

/**
 * Servlet implementation class RichiediAggiungiProdotto
 */
@WebServlet("/privato/prodotto/RichiediAggiungiProdottoInVendita")
public class RichiediAggiungiProdottoInVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediAggiungiProdottoInVendita() {
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
		CategoriaDAO cDAO = new CategoriaDAO();
		Vector<Categoria> categorie = cDAO.getAll();
		request.setAttribute("categorie", categorie);
		if ((session.getAttribute("Admin") != null) || (session.getAttribute("Venditore") != null)) {
			request.getRequestDispatcher("aggiungiProdottoInVendita.jsp").forward(request, response);
			return;
		} else {
			response.sendRedirect("/RichiediLoginVenditore");
			return;

		}
	}

}
