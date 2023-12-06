package it.unirc.db.ecommerce.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;
import it.unirc.db.ecommerce.utils.Utils;

/**
 * Servlet implementation class CreaNuovoVenditore
 */
@WebServlet("/CreaNuovoVenditore")
public class CreaNuovoVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaNuovoVenditore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String nomeAzienda = request.getParameter("nomeAzienda");
		BigDecimal pIVA;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String verificaPassword = request.getParameter("verificaPassword");
		VenditoreDAO venditoreDAO = new VenditoreDAO();
		Venditore venditore = new Venditore();
		Utils utils = new Utils();
		if (!utils.isEmailValid(email)) {
			request.setAttribute("emailErrata", true);
			request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
			return;
		}
		if (venditoreDAO.checkEmail(email)) {
			// Esiste giï¿½ l'email quindi lo devo rimandare alla jsp per fargli inserire
			// un'
			// altra email
			request.setAttribute("email", true);
			request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
			System.out.println("check email");
			return;
		}
		if (password.replaceAll("\\s", "") == "" || !password.replaceAll("\\s", "")
				.matches("^(?=.*[0-9])(?=.*[!@#$%^&*.])[a-zA-Z0-9!@#$%^&*.]{7,15}$")) {
			// E' vuota o la password non ï¿½ valida
			request.setAttribute("errorePassword", true);
			request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
			System.out.println("passw");
			return;
		}
		if (!password.equals(verificaPassword)) {
			// Le password non coincidono
			request.setAttribute("errorePasswordVerifica", true);
			request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
			System.out.println("vhevkpassw");
			System.out.println(password);
			System.out.println(verificaPassword);
			return;
		}

		if (nomeAzienda.trim().equals("")) {
			// È vuota
			request.setAttribute("erroreNome", true);
			request.getRequestDispatcher("registraNuovoVenditore.jspe.jsp").forward(request, response);
			System.out.println("nome");
			return;
		}

		String vat = request.getParameter("pIVA");
		if (vat.length() == 11) {
			pIVA = new BigDecimal(vat);

			try {
				pIVA = new BigDecimal(vat);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("errorePIVA", true);
				request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
				return;
			}
		} else {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
			return;
		}


		venditore.setNomeAzienda(nomeAzienda);
		venditore.setPIVA(pIVA);
		venditore.setEmail(email);
		venditore.setPassword(password);

		if (!venditoreDAO.salva(venditore)) {
			request.setAttribute("salva", true);
			request.getRequestDispatcher("registraNuovoVenditore.jsp").forward(request, response);
			return;
		} // L'ha salvato lo mando nell'index
		Venditore venditore2 = venditoreDAO.getNotById(venditore);
	    session.setAttribute("Venditore", venditore2);
	    response.sendRedirect("/privato/prodotto/RichiediAggiungiProdotto");
	}
}
