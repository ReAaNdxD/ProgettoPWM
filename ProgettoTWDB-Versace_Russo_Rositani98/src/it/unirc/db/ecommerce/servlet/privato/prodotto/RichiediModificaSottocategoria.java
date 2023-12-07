package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RichiediModificaProdotto
 */
@WebServlet("/privato/prodotto/RichiediModificaSottocategoria")
public class RichiediModificaSottocategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediModificaSottocategoria() {
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
		if (session.getAttribute("Admin") != null) {
			response.sendRedirect("creaSottocategoria.jsp");
		} else {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
	}

}
