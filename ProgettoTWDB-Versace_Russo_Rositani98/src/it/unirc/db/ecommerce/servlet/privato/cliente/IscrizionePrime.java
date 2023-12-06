package it.unirc.db.ecommerce.servlet.privato.cliente;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClientePrime;
import it.unirc.db.ecommerce.beans.ClientePrimeDAO;

/**
 * Servlet implementation class IscrizionePrime
 */
@WebServlet("/privato/cliente/IscrizionePrime")
public class IscrizionePrime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IscrizionePrime() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("idCliente"));
		if (session.getAttribute("idCliente") == null) {
			// Lo mando nel login
			response.sendRedirect("/SigninCliente");
			return;
		} // Ha fatto il login-->Mettendolo dentro web-inf non dovrò fare il check del
			// login nella jsp
		ClientePrimeDAO cpDAO = new ClientePrimeDAO();
		int id = (int) session.getAttribute("idCliente");
		System.out.println(id);
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
		System.out.println("Valore di name:" + request.getParameter("name"));
//		System.out.println("Valore di hidden:" + request.getParameter("hidden"));
		if (request.getParameter("name") == null) {
			//Significa che non ha selezionato niente
			response.getWriter().append("Errore generico :-(");
			return;
		}
		ClientePrime cliente = new ClientePrime(id);
		cliente.setDataInizioIscrizione(new Date(System.currentTimeMillis()));
		if (request.getParameter("name").equals("mensile")) {
			cliente.setMensile(true);
			System.out.println("mensile");
		} else if (request.getParameter("name").equals("annuale")) {
			cliente.setMensile(false);
			System.out.println("annuale");
		} else {
			// Significa che l'utente ha modificato qualcosa
			response.getWriter().append("Errore Hacker X(");
			return;
		} // All'interno del salva si fa il calcolo da solo della data di fine iscrizione
		if (!cpDAO.salva(cliente)) {
			request.setAttribute("salva", false);
			request.getRequestDispatcher("/WEB-INF/privato/cliente/iscrizionePrime.jsp").forward(request, response);
			return;
		} // Se l'ha salvato
			// Mi prelevo il calcolo della data di fine iscrizione riprendendomi il cliente
			// appena inserito
		cliente = cpDAO.get(new ClientePrime(id));
		// Vado a metterlo dentro la session per far comparire nella pagina di welcome i
		// suoi attributi
		session.setAttribute("clientePrime", cliente);
		System.out.println("Sono dentro IscrizionePrime:" + cliente);
		response.sendRedirect("/WelcomeToPrime.jsp");
	}
}
