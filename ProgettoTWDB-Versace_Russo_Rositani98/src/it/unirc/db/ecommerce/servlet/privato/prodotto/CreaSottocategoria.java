package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Categoria;
import it.unirc.db.ecommerce.beans.Sottocategoria;
import it.unirc.db.ecommerce.beans.SottocategoriaDAO;

/**
 * Servlet implementation class CreaCategoria
 */
@WebServlet("/privato/prodotto/CreaSottocategoria")
public class CreaSottocategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaSottocategoria() {
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
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		} else {

			String nome = null;
			String descrizione = null;
			Integer idCategoria = null;
			if (session.getAttribute("Categoria")==null) {
				try {
					idCategoria = Integer.parseInt(request.getParameter("categoria"));
				} catch (NumberFormatException e) {
					request.setAttribute("errore", true);
					request.getRequestDispatcher("RichiediCreaSottocategoria").forward(request, response);
					return;
				} 
			} else {
				idCategoria=((Categoria)session.getAttribute("Categoria")).getIdCategoria();
			}
			if (((String) request.getParameter("sottocategoria")).length() > 45) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("RichiediCreaSottocategoria").forward(request, response);
				return;
			} else {
				nome = request.getParameter("sottocategoria");
			}
			if (((String) request.getParameter("descrizione")).length() > 45) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("RichiediCreaSottocategoria").forward(request, response);
				return;
			} else {
				descrizione = request.getParameter("descrizione");
			}
			Sottocategoria sottocategoria = new Sottocategoria(nome, descrizione, idCategoria);
			SottocategoriaDAO sottocategoriaDAO = new SottocategoriaDAO();
			if (sottocategoriaDAO.getByNome(sottocategoria) == null) {
				if (sottocategoriaDAO.salva(sottocategoria)) {
					if (session.getAttribute("Categoria")!=null) {
						session.setAttribute("Categoria", null);
					}
					response.sendRedirect("VisualizzaProdottiInVendita");
					return;
				} else {
					request.setAttribute("errore", true);
					request.getRequestDispatcher("RichiediCreaSottocategoria").forward(request, response);
					System.out.println("non salva");
					return;
				}
			} else {
				request.setAttribute("presente", true);
				request.getRequestDispatcher("RichiediCreaSottocategoria").forward(request, response);
				return;
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
