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
import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;

/**
 * Servlet implementation class visualizzaProdotto
 */
@WebServlet("/privato/prodotto/VisualizzaProdotto")
public class VisualizzaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession()	;
		CategoriaDAO categoriaDAO= new CategoriaDAO();
		Vector<Categoria> categoria= categoriaDAO.getAll();
		request.setAttribute("categorie", categoria);
		if(session.getAttribute("Prodotto")!=null) {
			session.setAttribute("Prodotto",null);
		}
		if ((session.getAttribute("Venditore") != null) || (session.getAttribute("Admin")) != null) {
			if( (session.getAttribute("Admin")) != null) {
				VenditoreDAO venditoreDAO=new VenditoreDAO();
				Venditore venditore= venditoreDAO.get(new Venditore(1));
			     request.setAttribute("seller",venditore);
			}
				
			request.getRequestDispatcher("visualizzaProdotti.jsp").forward(request, response);;
			return;
		} else {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
	}

}
