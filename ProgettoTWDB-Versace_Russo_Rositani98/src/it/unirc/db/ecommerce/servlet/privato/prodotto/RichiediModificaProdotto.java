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
import it.unirc.db.ecommerce.beans.Sottocategoria;
import it.unirc.db.ecommerce.beans.SottocategoriaDAO;

/**
 * Servlet implementation class RichiediModificaProdotto
 */
@WebServlet("/privato/prodotto/RichiediModificaProdotto")
public class RichiediModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediModificaProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			System.out.println("gesfesgsefesf");
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
		int idProdotto = Integer.parseInt(request.getParameter("prodotto"));
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		Prodotto prodotto = prodottoDAO.get(new Prodotto(idProdotto));
		CategoriaDAO cDAO = new CategoriaDAO();
		SottocategoriaDAO scDAO = new SottocategoriaDAO();
		Categoria categoria = new Categoria();
		Sottocategoria sottocategoria = new Sottocategoria();
		Vector<Categoria> categorias = cDAO.getAll();
		sottocategoria = scDAO.get(new Sottocategoria(prodotto.getIdSottocategoria()));
		categoria = cDAO.get(new Categoria(sottocategoria.getIdCategoria()));
		if (prodotto != null) {
			session.setAttribute("Prodotto", prodotto);
			request.setAttribute("categorie", categorias);
			request.setAttribute("sottocategoria", sottocategoria);
			request.setAttribute("categoria", categoria);
		}
		request.setAttribute("categorie", categorias);
		request.getRequestDispatcher("modificaProdotto.jsp").forward(request, response);
		return;
	}

}
