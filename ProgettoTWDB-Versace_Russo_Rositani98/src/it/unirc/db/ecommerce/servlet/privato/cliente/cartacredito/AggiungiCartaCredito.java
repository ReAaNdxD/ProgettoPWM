package it.unirc.db.ecommerce.servlet.privato.cliente.cartacredito;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.Clinit;

import it.unirc.db.ecommerce.beans.CartaCredito;
import it.unirc.db.ecommerce.beans.CartaCreditoDAO;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.join.ClientePossiedeCartaDAO;
import oracle.net.aso.c;

/**
 * Servlet implementation class AggiungiCartaCredito
 */
@WebServlet("/privato/cliente/cartacredito/AggiungiCartaCredito")
public class AggiungiCartaCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiCartaCredito() {
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
		System.out.println(session.getAttribute("idCliente"));
		int idCliente = 0;
		if (session.getAttribute("idCliente") != null)
			idCliente = (int) session.getAttribute("idCliente");
		else {// non ha fatto il login
			response.sendRedirect("/login.jsp");
			return;
		}
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
			request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
			return;
		}
		if (request.getParameter("intestatario") == null) {
//			response.sendRedirect("/errore.html");
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
			return;
		}
		String intestatario = request.getParameter("intestatario");
		Date date;
		try {
			date = Date.valueOf(request.getParameter("dataScadenza"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
			return;
		}
		if (request.getParameter("codiceSicurezza").trim().length() != 3) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
			return;
		}
		Short codiceSicurezza;
		try {
			codiceSicurezza = Short.valueOf(request.getParameter("codiceSicurezza"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			response.sendRedirect("/errore.html");
			request.setAttribute("errore", true);
			request.getRequestDispatcher("aggiungiCartaCredito.jsp").forward(request, response);
			return;
		}
		Cliente cliente = new Cliente(idCliente);
		CartaCredito c = new CartaCredito();
		c.setCodiceSicurezza(codiceSicurezza);
		c.setIntestatario(intestatario);
		c.setNumeroCarta(numCarta);
		c.setDataScadenza(date);
		CartaCreditoDAO cDAO = new CartaCreditoDAO();
		boolean creazione = cDAO.salva(c, cliente);
		// Il salva di cartacreditoDAO fa automaticamente l'inserimento nella join di
		// possiede ;-)
		if (creazione)
			response.sendRedirect("/privato/cliente/cartacredito/VisualizzaCarteCredito");
		else
			response.getWriter().append("Errore inserimento non effettuato");
	}

}
