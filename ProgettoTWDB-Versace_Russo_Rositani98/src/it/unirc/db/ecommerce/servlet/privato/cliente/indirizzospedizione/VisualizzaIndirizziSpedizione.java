package it.unirc.db.ecommerce.servlet.privato.cliente.indirizzospedizione;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizione;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizioneDAO;

/**
 * Servlet implementation class VisualizzaIndirizziSpedizione
 */
@WebServlet("/privato/cliente/indirizzospedizione/VisualizzaIndirizziSpedizione")

public class VisualizzaIndirizziSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaIndirizziSpedizione() {
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
		if(session.getAttribute("idCliente")!=null) {
			//Se ha fatto il login
			int idCliente = (int) session.getAttribute("idCliente");
		IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
		//Mi prendo tutti gli indirizzi del cliente loggato
		Vector<IndirizzoSpedizione> indirizzi = isDAO.getAll(new Cliente(idCliente));
		request.setAttribute("indirizzi", indirizzi);
		request.getRequestDispatcher("/WEB-INF/privato/cliente/indirizzospedizione/visualizzaIndirizziSpedizione.jsp")
				.forward(request, response);
		}else {
			response.sendRedirect("/SigninCliente");
		}
	}

}
