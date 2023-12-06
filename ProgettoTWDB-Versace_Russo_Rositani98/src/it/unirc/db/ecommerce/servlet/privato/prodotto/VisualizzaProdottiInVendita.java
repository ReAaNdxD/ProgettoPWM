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
import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;
import it.unirc.db.ecommerce.beans.SottocategoriaDAO;
import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;
import it.unirc.db.ecommerce.views.ViewProductSeller;

/**
 * Servlet implementation class AggiungiArticoloInVendita
 */
@WebServlet("/privato/prodotto/VisualizzaProdottiInVendita")
public class VisualizzaProdottiInVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaProdottiInVendita() {
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
		System.out.println(session.getAttribute("Venditore"));
		if(session.getAttribute("Prodotto")!=null) {
			session.setAttribute("Prodotto",null);
		}
		if ((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}			
		CategoriaDAO cDAO = new CategoriaDAO();
		Vector<Categoria> categoria = cDAO.getAll();
		request.setAttribute("categoria", categoria);
		request.getRequestDispatcher("/privato/prodotto/prodottiInVendita.jsp").forward(request, response);
		return;
	}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
