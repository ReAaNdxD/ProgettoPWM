package it.unirc.db.ecommerce.servlet.privato.cliente.indirizzospedizione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.IndirizzoSpedizione;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizioneDAO;

/**
 * Servlet implementation class RimuoviIndirizzoSpedizione
 */
@WebServlet("/privato/cliente/indirizzospedizione/RimuoviIndirizzoSpedizione")
public class RimuoviIndirizzoSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimuoviIndirizzoSpedizione() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("idCliente") != null) {
			int idIndirizzo = Integer.parseInt(request.getParameter("id"));
			IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
			IndirizzoSpedizione i = new IndirizzoSpedizione(idIndirizzo);
			if (isDAO.elimina(i))// Passo alla servlet
				response.sendRedirect("VisualizzaIndirizziSpedizione");
			else {
				request.getSession().setAttribute("eliminazioneIS", true);
				response.sendRedirect("/privato/cliente/indirizzospedizione/VisualizzaIndirizziSpedizione");
			}
		} else {
			// Non ha fatto il login
			response.sendRedirect("/SigninCliente");
		}
	}

}
