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
 * Servlet implementation class AggiungiIndirizzoSpedizione
 */
@WebServlet("/privato/cliente/indirizzospedizione/AggiungiIndirizzoSpedizione")
public class AggiungiIndirizzoSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiIndirizzoSpedizione() {
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
		if (session.getAttribute("idCliente") != null) {
//		response.getWriter().append("SUCCESS");
			int idCliente = (int) session.getAttribute("idCliente");

			IndirizzoSpedizione is = new IndirizzoSpedizione();
			if (request.getParameter("regione").replaceAll("\\s", "") == ""
					|| !request.getParameter("regione").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				// CAcciando tutti gli spazi vedi se è vuoto o se non contiene solo lettere
				// allora:
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			String regione = request.getParameter("regione");
			if (request.getParameter("provincia").replaceAll("\\s", "") == ""
					|| request.getParameter("provincia").replaceAll("\\s", "").length() != 2
					|| !request.getParameter("provincia").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			String provincia = request.getParameter("provincia").toUpperCase();
			if (request.getParameter("citta").replaceAll("\\s", "") == ""
					|| !request.getParameter("citta").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			String citta = request.getParameter("citta");
			if (request.getParameter("via").replaceAll("\\s", "") == ""
					|| !request.getParameter("via").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			String via = request.getParameter("via");
			if (request.getParameter("numeroCivico").replaceAll("\\s", "") == ""
					|| request.getParameter("numeroCivico").replaceAll("\\s", "").length() != 3) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			String numeroCivico = request.getParameter("numeroCivico");
			if (request.getParameter("cap").replaceAll("\\s", "").length() != 5) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			try {
				int cap = Integer.parseInt(request.getParameter("cap"));
				is.setCap(cap);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			is.setRegione(regione);
			is.setProvincia(provincia);
			is.setVia(via);
			is.setNCivico(numeroCivico);
			is.setCitta(citta);
			if (request.getParameter("numeroTelefonico").replaceAll("\\s", "").length() != 10) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			try {
				BigDecimal telefono = new BigDecimal(request.getParameter("numeroTelefonico"));
				is.setTelefono(telefono);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiIndirizzoSpedizione.jsp").forward(request, response);
				return;
			}
			is.setIdCliente(idCliente);

			// Provo a completare l'inserimento
			IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
			if (isDAO.salva(is))
				response.sendRedirect("VisualizzaIndirizziSpedizione");
			else
				response.getWriter().append("Errore inserimento non effettuato");
		} else {// Non ha fatto il LOGIN lo rimando alla pagina di login come fa amazon
			response.sendRedirect("/SigninCliente");
		}
	}

}
