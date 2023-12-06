package it.unirc.db.ecommerce.servlet.privato.cliente.ordine;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Carrello;
import it.unirc.db.ecommerce.beans.CarrelloDAO;
import it.unirc.db.ecommerce.beans.CartaCredito;
import it.unirc.db.ecommerce.beans.CartaCreditoDAO;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClienteDAO;
import it.unirc.db.ecommerce.beans.ClientePrimeDAO;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizione;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizioneDAO;
import it.unirc.db.ecommerce.beans.MetodoSpedizione;
import it.unirc.db.ecommerce.beans.MetodoSpedizioneDAO;
import it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO;

/**
 * Servlet implementation class RichiediCheckOut
 */
@WebServlet("/privato/cliente/ordine/RichiediCheckOut")
public class RichiediCheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediCheckOut() {
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
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		int idCliente = (int) session.getAttribute("idCliente");
		ClienteDAO cDAO = new ClienteDAO();
		ClientePrimeDAO cpDAO = new ClientePrimeDAO();
		Cliente c = cDAO.get(new Cliente(idCliente));
		CartaCreditoDAO creditoDAO = new CartaCreditoDAO();
		MetodoSpedizioneDAO metodoSpedizioneDAO = new MetodoSpedizioneDAO();
		Vector<MetodoSpedizione> metodi = metodoSpedizioneDAO.getAllMetodoSpedizione();
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Carrello carrello = carrelloDAO.getAttivo(idCliente);
		if(cpDAO.isPrime(c)) {
			request.setAttribute("prime", true);
			System.out.println("prime");
		}
		
		System.out.println("Carrello : "+carrello);
		// MI prendo gli indirizzi
		IndirizzoSpedizioneDAO indirizzoSpedizioneDAO = new IndirizzoSpedizioneDAO();
		Vector<IndirizzoSpedizione> indirizzi = indirizzoSpedizioneDAO.getAll(c);
		ArticoloComponeCarrelloDAO aDAO = new ArticoloComponeCarrelloDAO();
		Vector<CartaCredito> carte = creditoDAO.getAllByCliente(c);
		
		for (CartaCredito cartaCredito : carte) {
			System.out.println(cartaCredito);
		}
		double costo = aDAO.calcoloCosto(carrello);
		request.setAttribute("costo", costo);
		request.setAttribute("carrello", carrello);
		request.setAttribute("indirizzi", indirizzi);
		request.setAttribute("metodi", metodi);
		request.setAttribute("carte", carte);
		request.getRequestDispatcher("/WEB-INF/privato/cliente/ordine/checkout.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
