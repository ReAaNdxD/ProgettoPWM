package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.beans.Categoria;
import it.unirc.db.ecommerce.beans.CategoriaDAO;
import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;
import it.unirc.db.ecommerce.beans.Sottocategoria;
import it.unirc.db.ecommerce.beans.SottocategoriaDAO;
import it.unirc.db.ecommerce.beans.Venditore;

/**
 * Servlet implementation class RichiediModificaProdotto
 */
@WebServlet("/privato/prodotto/RichiediModificaProdottoInVendita")
public class RichiediModificaProdottoInVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediModificaProdottoInVendita() {
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

		int idArticolo = Integer.parseInt(request.getParameter("articolo"));
		ArticoloDAO articoloDAO = new ArticoloDAO();
		Articolo articolo = articoloDAO.get(new Articolo(idArticolo));
		if (articolo==null) {
			response.sendRedirect("VisualizzaProdottiInVendita");
			return;
		}
		session.setAttribute("Articolo", articolo);
		ProdottoDAO pDAO = new ProdottoDAO();
		Prodotto prodotto = pDAO.get(new Prodotto(articolo.getIdProdotto()));
		SottocategoriaDAO sottocategoriaDAO = new SottocategoriaDAO();
		Sottocategoria sottocategoria = sottocategoriaDAO.get(new Sottocategoria(prodotto.getIdSottocategoria()));
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.get(new Categoria(sottocategoria.getIdCategoria()));
		Venditore venditore = (Venditore) session.getAttribute("Venditore");
		request.setAttribute("categoria", categoria);
		request.setAttribute("sottocategoria", sottocategoria);
		request.setAttribute("prodotto", prodotto);
	
		if ((session.getAttribute("Admin") != null) || (session.getAttribute("Venditore") != null)) {
			if (session.getAttribute("Admin") != null) {
				request.getRequestDispatcher("modificaProdottoInVendita.jsp").forward(request, response);
				return;

			} else if (articolo.getIdVenditore() == venditore.getIdVenditore()) {
				request.getRequestDispatcher("modificaProdottoInVendita.jsp").forward(request, response);
				return;
			}  else {
				response.sendRedirect("VisualizzaProdottiInVendita");
				return;
			}
		} else {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
