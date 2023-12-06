package it.unirc.db.ecommerce.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClientePrimeDAO;

/**
 * Servlet implementation class RichiediIscrizionePrime
 */
@WebServlet("/RichiediIscrizionePrime")
public class RichiediIscrizionePrime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediIscrizionePrime() {
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
		if (session.getAttribute("idCliente") == null) {
			// Lo mando nel login
			response.sendRedirect("/SigninCliente");
			return;
		} // Ha fatto il login-->Mettendolo dentro web-inf non dovrò fare il check del
			// login nella jsp
		ClientePrimeDAO cpDAO = new ClientePrimeDAO();
		int id = (int) session.getAttribute("idCliente");
		if (!cpDAO.vincoloCL1(id)) {
			// Se non ha la carta di credito non può diventar prime
			System.out.println("Il cliente non possiede una carta di credito");
			response.sendRedirect("/privato/cliente/cartacredito/RichiediAggiungiCartaCredito");
			return;
		}
		if (cpDAO.isPrime(new Cliente(id))) {
			// E' già prime
			response.getWriter().append("Sei già prime ;-)");
			return;
		}
//		if (!cpDAO.vincoloCL1(session.getAttribute("idCliente")) {
//			System.out.println("Il cliente non possiede una carta di credito");
//			return false;
//		}
//		if (isPrime(new Cliente(clientePrime.getIdCliente()))) {
//			System.out.println("Il cliente è già prime");
//			return false;
		request.getRequestDispatcher("/WEB-INF/privato/cliente/iscrizionePrime.jsp").forward(request, response);
		;
	}

}
