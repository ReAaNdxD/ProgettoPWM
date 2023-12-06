package it.unirc.db.ecommerce.servlet.privato.cliente.indirizzospedizione;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.IndirizzoSpedizione;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizioneDAO;

/**
 * Servlet implementation class ModificaIndirizzoSpedizione
 */
@WebServlet("/privato/cliente/indirizzospedizione/ModificaIndirizzoSpedizione")
public class ModificaIndirizzoSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaIndirizzoSpedizione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//Il check del login lo faccio più in basso
		HttpSession session = request.getSession();
		if (session.getAttribute("indirizzo") == null) {
			response.getWriter().append("Errore");
			return;
		}
		if (session.getAttribute("idCliente") != null) {
			int idCliente = (int) session.getAttribute("idCliente");

			IndirizzoSpedizione is = (IndirizzoSpedizione) session.getAttribute("indirizzo");

			if (request.getParameter("regione").replaceAll("\\s", "") == ""
					|| !request.getParameter("regione").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				// CAcciando tutti gli spazi vedi se è vuoto o se non contiene solo lettere
				// allora:
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			String regione = request.getParameter("regione");
			if (request.getParameter("provincia").replaceAll("\\s", "") == ""
					|| request.getParameter("provincia").replaceAll("\\s", "").length() != 2
					|| !request.getParameter("provincia").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			String provincia = request.getParameter("provincia").toUpperCase();
			if (request.getParameter("citta").replaceAll("\\s", "") == ""
					|| !request.getParameter("citta").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			String citta = request.getParameter("citta");
			if (request.getParameter("via").replaceAll("\\s", "") == ""
					|| !request.getParameter("via").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			String via = request.getParameter("via");
			if (request.getParameter("numeroCivico").replaceAll("\\s", "") == ""
					|| request.getParameter("numeroCivico").replaceAll("\\s", "").length() != 3) {
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			String numeroCivico = request.getParameter("numeroCivico");
			if (request.getParameter("cap").replaceAll("\\s", "").length() != 5) {
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			try {
				int cap = Integer.parseInt(request.getParameter("cap"));
				is.setCap(cap);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			is.setRegione(regione);
			is.setProvincia(provincia);
			is.setVia(via);
			is.setNCivico(numeroCivico);
			is.setCitta(citta);
			if (request.getParameter("numeroTelefonico").replaceAll("\\s", "").length() != 10) {
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			try {
				BigDecimal telefono = new BigDecimal(request.getParameter("numeroTelefonico"));
				is.setTelefono(telefono);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				session.setAttribute("modificaIS", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
				return;
			}
			is.setIdCliente(idCliente);

			// Provo a completare l'inserimento
			IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
			if (isDAO.modifica(is)) {
				if (request.getParameter("preferito") != null) {
					// Perchè se non ha selezionato la checkbox il value non restituisce niente al
					// server
					System.out.println(request.getParameter("preferito"));
					if (!isDAO.modificaPreferito(is)) {
						// Errore non sono riuscito a metterlo preferito;
						session.setAttribute("preferitoIS", true);
						response.sendRedirect("RichiediModificaIndirizzoSpedizione");
						return;
					}
				}
				// Se è andato tutto bene
				session.setAttribute("indirizzo", null);
				response.sendRedirect("VisualizzaIndirizziSpedizione");
				System.out.println("SOno qui");
			} else {
				// Non è riuscito nella modifica
				session.setAttribute("modification", true);
				response.sendRedirect("RichiediModificaIndirizzoSpedizione");
			}
		} else {// Non ha fatto il LOGIN lo rimando alla pagina di login come fa amazon
			response.sendRedirect("/SigninCliente");
		}
	}

//			if (request.getParameter("citta").trim() == "") {
//				request.setAttribute("modificaIS", true);
//				request.getRequestDispatcher("modificaIndirizzoSpedizione.jsp").forward(request, response);
//				return;
//			}
//			String citta = request.getParameter("citta");
//			if (request.getParameter("via").trim() == "") {
//				request.setAttribute("modificaIS", true);
//				request.getRequestDispatcher("modificaIndirizzoSpedizione.jsp").forward(request, response);
//				return;
//			}
//			String via = request.getParameter("via");
//			if (request.getParameter("numeroCivico").trim() == "") {
//				request.setAttribute("modificaIS", true);
//				request.getRequestDispatcher("modificaIndirizzoSpedizione.jsp").forward(request, response);
//				return;
//			}
//			String numeroCivico = request.getParameter("numeroCivico");
//			try {
//				int cap = Integer.parseInt(request.getParameter("cap"));
//				is.setCap(cap);
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				request.setAttribute("modificaIS", true);
//				request.getRequestDispatcher("modificaIndirizzoSpedizione.jsp").forward(request, response);
//				return;
//			}
//			is.setRegione(regione);
//			is.setProvincia(provincia);
//			is.setVia(via);
//			is.setNCivico(numeroCivico);
//			is.setCitta(citta);
//			try {
//				BigDecimal telefono = new BigDecimal(request.getParameter("numeroTelefonico"));
//				is.setTelefono(telefono);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				request.setAttribute("modificaIS", true);
//				request.getRequestDispatcher("modificaIndirizzoSpedizione.jsp").forward(request, response);
//				return;
//			}
//			is.setIdCliente(idCliente);
//
////			// Provo a completare l'inserimento
////			IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
////			if (isDAO.modifica(is))
////				response.sendRedirect("VisualizzaIndirizziSpedizione");
////			else
////				response.getWriter().append("Errore inserimento non effettuato");
////		} else {// Non ha fatto il LOGIN lo rimando alla pagina di login come fa amazon
////			response.sendRedirect("/login.html");
////		}
////	}

}
