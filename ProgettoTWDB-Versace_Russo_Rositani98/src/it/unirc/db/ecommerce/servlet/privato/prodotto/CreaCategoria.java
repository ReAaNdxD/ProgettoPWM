package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Categoria;
import it.unirc.db.ecommerce.beans.CategoriaDAO;

/**
 * Servlet implementation class CreaCategoria
 */
@WebServlet("/privato/prodotto/CreaCategoria")
public class CreaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaCategoria() {
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
		if (session.getAttribute("Admin") == null) {
			request.getRequestDispatcher("/loginVenditore.jsp").forward(request, response);
			return;
		} else {
			String nome = null;
			String descrizione = null;
			if (((String) request.getParameter("categoria")).length() > 45) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("creaCategoria.jsp").forward(request, response);
				return;
			} else {
				nome = request.getParameter("categoria");
			}
			if (((String) request.getParameter("descrizione")).length() > 45) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("creaCategoria.jsp").forward(request, response);
				return;
			} else {
				descrizione = request.getParameter("descrizione");
			}
			Categoria categoria = new Categoria(nome, descrizione);
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			if (categoriaDAO.getByNome(categoria) == null) {
				if (categoriaDAO.salva(categoria)) {
					Categoria categoria2 = categoriaDAO.getByNome(categoria);
					session.setAttribute("Categoria", categoria2);
					response.sendRedirect("RichiediCreaSottocategoria");
					return;
				} else {
					request.setAttribute("errore", true);
					request.getRequestDispatcher("creaCategoria.jsp").forward(request, response);
					return;
				}
			} else {
				request.setAttribute("presente", true);
				request.getRequestDispatcher("creaCategoria.jsp").forward(request, response);
				return;
			}
		}
	}
}
