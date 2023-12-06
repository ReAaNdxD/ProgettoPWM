package it.unirc.db.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.ClienteDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email.replaceAll("\\s", "") == "" || !email.replaceAll("\\s", "").matches(
				"^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
			// E' vuota o non è un email valida
			request.setAttribute("email", true);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			System.out.println("email");
			return;
		}
		if (password.replaceAll("\\s", "") == "") {
			// E' vuota o la password non è valida
			request.setAttribute("password", true);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			System.out.println("passw");
			return;
		}

		ClienteDAO cDAO = new ClienteDAO();
		Integer idCliente = cDAO.login(email, password);
		if (idCliente == null) {
			// Non c'è nessun account che corrisponda all'email e alla password inserita
			request.setAttribute("login", false);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			System.out.println("login");
			return;
		}
		// LOGIN RIUSCITO => lo metto dentro la sessione
		HttpSession session = request.getSession();
		session.setAttribute("idCliente", idCliente);
		System.out.println(request.getParameter("checkbox1"));
		if (request.getParameter("checkbox1") != null) {
			// Significa che ha selezionato la checkbox
			// I cookie non sono altro che oggetti in java
			Cookie cookie = new Cookie("emailDAGCliente", email);
			// Noi attraverso i cookie possiamo memorizzare solo stringhe
			// Ricordati dobbiamo stabilire la scadenza di questo cookie:
			cookie.setMaxAge(60 * 60 * 24);
			cookie.setHttpOnly(true);// cosi facendo i javascript non potranno utilizzarlo
			// Devo passare il cookie al browser web
			response.addCookie(cookie);
		}
		response.sendRedirect("/");
//		if(vDAO.login(venditore)) {
//			HttpSession session = request.getSession();
//			session.setAttribute("autenticato", true);
//			
//			response.sendRedirect("/privato/studente/VisualizzaStudenti");
//		}else {
	}
}
