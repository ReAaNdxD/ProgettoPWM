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
@WebServlet("/privato/prodotto/ModificaProdotto")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProdotto() {
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
			System.out.println("gesfesgsefesf");
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
		Integer idSottocategoria = 0;
		String nome = null;
		String marca = null;
		String descBreve = null;
		String descDettagliata = null;
//		if ((session.getAttribute("Admin") == null) || (session.getAttribute("Venditore") == null)) {
//			response.sendRedirect("/");
//		}
		
		int idProdotto=((Prodotto)session.getAttribute("Prodotto")).getIdProdotto();
		
		try {
			idSottocategoria = Integer.parseInt(request.getParameter("sottocategoria"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
			return;
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
			return;
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

		Prodotto prodotto= new Prodotto(idProdotto, nome, marca, descBreve, descDettagliata, idSottocategoria);
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		
			if (prodottoDAO.modifica(prodotto)) {
				request.getRequestDispatcher("visualizzaProdotti.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}

		}
}
