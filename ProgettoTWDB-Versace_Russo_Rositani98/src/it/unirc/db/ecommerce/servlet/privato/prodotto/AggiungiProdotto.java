package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;

/**
 * Servlet implementation class AggiungiProdotto
 */
@WebServlet("/privato/prodotto/AggiungiProdotto")
public class AggiungiProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if ((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
		int idSottocategoria = 0;
		String nome = null;
		String marca = null;
		String descBreve = null;
		String descDettagliata = null;
		try {
			idSottocategoria = Integer.parseInt(request.getParameter("sottocategoria"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
		}
		if (request.getParameter("prodotto").length() > 45) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
			return;
		} else {
			nome = request.getParameter("prodotto");
		}
		if (request.getParameter("marca").length() > 45) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
		} else {
			marca = request.getParameter("marca");
		}
		if (request.getParameter("breve").length() > 45) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
			return;
		} else {
			descBreve = request.getParameter("breve");
		}
		if (request.getParameter("dettagliata").length() > 140) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
			return;
		} else {
			descDettagliata = request.getParameter("dettagliata");
		}

		Prodotto prodotto = new Prodotto(nome, marca, descBreve, descDettagliata, idSottocategoria);
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		if (prodottoDAO.getIdProdottoByNomeAndMarca(prodotto)) {
			request.setAttribute("presente", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
			return;

		} else {
			if (prodottoDAO.salva(prodotto)) {
				Prodotto prodotto2 = prodottoDAO.getNotById(prodotto);
				System.out.println(prodotto2);
				session.setAttribute("Prodotto", prodotto2);
				request.getRequestDispatcher("aggiungiProdottoInVendita.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
				return;
			}

		}
	}

}
