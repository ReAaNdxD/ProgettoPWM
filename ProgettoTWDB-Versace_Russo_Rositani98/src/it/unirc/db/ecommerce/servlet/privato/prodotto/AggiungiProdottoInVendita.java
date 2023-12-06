package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

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
import it.unirc.db.ecommerce.beans.Venditore;

/**
 * Servlet implementation class AggiungiProdotto
 */
@WebServlet("/privato/prodotto/AggiungiProdottoInVendita")
public class AggiungiProdottoInVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiProdottoInVendita() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * \
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Articolo a = new Articolo();
		ArticoloDAO aDAO = new ArticoloDAO();
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		CategoriaDAO cDAO = new CategoriaDAO();
	    Vector<Categoria> categorie = cDAO.getAll();
	    request.setAttribute("categorie", categorie);
		float prezzo;
		Integer idProdotto = null;
		double price = 0;
		if (session.getAttribute("Venditore") != null) {
			Venditore venditore = (Venditore) session.getAttribute("Venditore");
			a.setIdVenditore(venditore.getIdVenditore());
		} else if (session.getAttribute("Admin") != null) {
				a.setIdVenditore(1);
		} else {
			response.sendRedirect("/RichiediLoginVenditore");
			return;

		}
		if (session.getAttribute("Prodotto") != null) {
			Prodotto product = (Prodotto) session.getAttribute("Prodotto");
			System.out.println("CI sono");
			idProdotto = product.getIdProdotto();
			a.setIdProdotto(idProdotto);
		} else {
			if ((request.getParameter("prodotto") != null)) {
				System.out.println(request.getParameter("prodotto"));
				if (prodottoDAO.getIdProdottoByNome(request.getParameter("prodotto")) > 0) {
					idProdotto = prodottoDAO.getIdProdottoByNome(request.getParameter("prodotto"));
					a.setIdProdotto(idProdotto);
				} else {
					System.out.println("ELSE PRODOTTOBYNOME");
					request.setAttribute("errore", true);
					request.getRequestDispatcher("aggiungiProdottoInVendita.jsp").forward(request, response);
					return;
				}
			}
		}
		if ((request.getParameter("prezzo") != null) && (!request.getParameter("prezzo").trim().equals(""))) {
			try {
				price = Double.parseDouble(request.getParameter("prezzo"));

			} catch (NumberFormatException e) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiProdottoInVendita.jsp").forward(request, response);
				return;
			}
		}

		prezzo = BigDecimal.valueOf(price).setScale(2, RoundingMode.DOWN).floatValue();
		a.setPrezzo(prezzo);
		a.setQuantita(0);
		if (aDAO.isExist(a)) {
			request.setAttribute("venduto", true);
			request.getRequestDispatcher("aggiungiProdottoInVendita.jsp").forward(request, response);
		} else {
			if (aDAO.salva(a)) {
				System.out.println("if Salva");
				session.setAttribute("Prodotto", null);
				request.getRequestDispatcher("VisualizzaProdottiInVendita").forward(request, response);
				return;
			} else {
				System.out.println("else salva");
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiProdottoInVendita.jsp").forward(request, response);
				return;

			}
		}
	}
}
