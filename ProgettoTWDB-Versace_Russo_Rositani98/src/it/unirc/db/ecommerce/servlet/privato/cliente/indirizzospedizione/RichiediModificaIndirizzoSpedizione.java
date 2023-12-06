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
 * Servlet implementation class RichiediModificaIndirizzoSpedizione
 */
@WebServlet("/privato/cliente/indirizzospedizione/RichiediModificaIndirizzoSpedizione")
public class RichiediModificaIndirizzoSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediModificaIndirizzoSpedizione() {
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
			response.sendRedirect("/SigninCliente");
			return;
		}
		if (request.getParameter("id") == null) {
			// Significa che qualcosa non è andato a buon fine nella servlet
			// ModificaIndirizzoSpedizione e
			// quindi per sorvolare il passaggio del parametro id alla servlet faccio il
			// blocco if else
			// Perchè è questa Servlet che fa il check se è preferito allora mi setto
			// l'attributo affinche mi compaia nella jsp la checkbox
			System.out.println("Sono dentro l'if");
			IndirizzoSpedizione i = (IndirizzoSpedizione) session.getAttribute("indirizzo");
			System.out.println(i.isPreferito());
			if (i.isPreferito())
				request.setAttribute("preferito", true);
		} else {
			System.out.println("Sono dentro l'else");
			int idIndirizzo = Integer.parseInt(request.getParameter("id"));
			IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
			IndirizzoSpedizione i = isDAO.get(new IndirizzoSpedizione(idIndirizzo));
			System.out.println(i.toString());
			session.setAttribute("indirizzo", i);
			System.out.println(i.isPreferito());
			if (i.isPreferito())
				request.setAttribute("preferito", true);
		}
		System.out.println("Sto per mandare la richiesta");
		request.getRequestDispatcher("/WEB-INF/privato/cliente/indirizzospedizione/modificaIndirizzoSpedizione.jsp")
				.forward(request, response);
	}
}
