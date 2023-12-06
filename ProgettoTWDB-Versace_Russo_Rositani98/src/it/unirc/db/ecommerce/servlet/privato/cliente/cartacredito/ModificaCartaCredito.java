package it.unirc.db.ecommerce.servlet.privato.cliente.cartacredito;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.CartaCredito;
import it.unirc.db.ecommerce.beans.CartaCreditoDAO;
import it.unirc.db.ecommerce.beans.Cliente;

/**
 * Servlet implementation class ModificaCartaCredito
 */
@WebServlet("/privato/cliente/cartacredito/ModificaCartaCredito")
public class ModificaCartaCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaCartaCredito() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("idCliente")==null) {
			response.sendRedirect("/login.jsp");
			return;
		}
		if (session.getAttribute("cartacredito") != null) {
			CartaCredito c = (CartaCredito) session.getAttribute("cartacredito");
			session.setAttribute("cartacredito", null);
			int idCliente = (int)session.getAttribute("idCliente");
			if (request.getParameter("numeroCarta").trim().length() > 16
					|| request.getParameter("numeroCarta").trim().length() < 13) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
				return;
			}
			BigDecimal numCarta;
			try {
				numCarta = new BigDecimal(request.getParameter("numeroCarta"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
//			response.sendRedirect("/errore.html");
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaCartaCredito.jsp").forward(request, response);
				return;
			}
			if (request.getParameter("intestatario") == null) {
//			response.sendRedirect("/errore.html");
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaCartaCredito.jsp").forward(request, response);
				return;
			}
			String intestatario = request.getParameter("intestatario");
			Date date;
			try {
				date = Date.valueOf(request.getParameter("dataScadenza"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaCartaCredito.jsp").forward(request, response);
				return;
			}
			if (request.getParameter("codiceSicurezza").trim().length() != 3) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaCartaCredito.jsp").forward(request, response);
				return;
			}
			Short codiceSicurezza;
			try {
				codiceSicurezza = Short.valueOf(request.getParameter("codiceSicurezza"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				response.sendRedirect("/errore.html");
				request.setAttribute("errore", true);
				request.getRequestDispatcher("modificaCartaCredito.jsp").forward(request, response);
				return;
			}
			c.setIntestatario(intestatario);
			c.setNumeroCarta(numCarta);
			c.setDataScadenza(date);
			c.setCodiceSicurezza(codiceSicurezza);
			CartaCreditoDAO cDAO = new CartaCreditoDAO();
			boolean creazione = cDAO.modifica(c);
			// Il salva di cartacreditoDAO fa automaticamente l'inserimento nella join di
			// possiede ;-)
			if (creazione)
				response.sendRedirect("VisualizzaCarteCredito");
			else
				response.getWriter().append("Errore inserimento non effettuato");
		} else {
			response.getWriter().append("Non c'e l'attributo cartacredito");
		}
	}
}
