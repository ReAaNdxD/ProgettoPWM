package it.unirc.db.ecommerce.servlet.privato.cliente.ordine;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

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
import it.unirc.db.ecommerce.beans.IndirizzoSpedizione;
import it.unirc.db.ecommerce.beans.IndirizzoSpedizioneDAO;
import it.unirc.db.ecommerce.beans.MetodoSpedizione;
import it.unirc.db.ecommerce.beans.MetodoSpedizioneDAO;
import it.unirc.db.ecommerce.beans.Ordine;
import it.unirc.db.ecommerce.beans.OrdineDAO;
import it.unirc.db.ecommerce.beans.Pagamento;
import it.unirc.db.ecommerce.beans.PagamentoDAO;
import it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/privato/cliente/ordine/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}

		int idCliente = (int) session.getAttribute("idCliente");
		IndirizzoSpedizioneDAO isDAO = new IndirizzoSpedizioneDAO();
		int idIndirizzo;
		if (!isDAO.nonCiSonoIndirizzi(idCliente)) {
			// Se ha indirizzi
			try {
				idIndirizzo = Integer.parseInt(request.getParameter("idIndirizzo"));
			} catch (NumberFormatException e) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}

			System.out.println("idIndirizzo : " + idIndirizzo);
		} else {

			IndirizzoSpedizione is = new IndirizzoSpedizione();
			if (request.getParameter("regione").replaceAll("\\s", "") == ""
					|| !request.getParameter("regione").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				// CAcciando tutti gli spazi vedi se è vuoto o se non contiene solo lettere
				// allora:
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			String regione = request.getParameter("regione");
			if (request.getParameter("provincia").replaceAll("\\s", "") == ""
					|| request.getParameter("provincia").replaceAll("\\s", "").length() != 2
					|| !request.getParameter("provincia").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			String provincia = request.getParameter("provincia").toUpperCase();
			if (request.getParameter("citta").replaceAll("\\s", "") == ""
					|| !request.getParameter("citta").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			String citta = request.getParameter("citta");
			if (request.getParameter("via").replaceAll("\\s", "") == ""
					|| !request.getParameter("via").replaceAll("\\s", "").matches("[a-zA-Z]+")) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			String via = request.getParameter("via");
			if (request.getParameter("numeroCivico").replaceAll("\\s", "") == ""
					|| request.getParameter("numeroCivico").replaceAll("\\s", "").length() != 3) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			String numeroCivico = request.getParameter("numeroCivico");
			if (request.getParameter("cap").replaceAll("\\s", "").length() != 5) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			try {
				int cap = Integer.parseInt(request.getParameter("cap"));
				is.setCap(cap);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			is.setRegione(regione);
			is.setProvincia(provincia);
			is.setVia(via);
			is.setNCivico(numeroCivico);
			is.setCitta(citta);
			if (request.getParameter("numeroTelefonico").replaceAll("\\s", "").length() != 10) {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			try {
				BigDecimal telefono = new BigDecimal(request.getParameter("numeroTelefonico"));
				is.setTelefono(telefono);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("errore", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			is.setIdCliente(idCliente);

			// Provo a completare l'inserimento
			if (isDAO.salva(is)) {
				IndirizzoSpedizione indirizzoSpedizione = isDAO.getIndirizzoByCliente(is);
				idIndirizzo = indirizzoSpedizione.getIdIndirizzoSpedizione();
			} else {
				request.setAttribute("salva", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
		}
		CartaCreditoDAO cDAO = new CartaCreditoDAO();
		int idCarta;
		if (cDAO.hasCartaCredito(idCliente)) {

			try {
				idCarta = Integer.parseInt(request.getParameter("idCarta"));
			} catch (NumberFormatException e) {
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
		} else {
			if (request.getParameter("numeroCarta").trim().length() > 16
					|| request.getParameter("numeroCarta").trim().length() < 13) {
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			BigDecimal numCarta;
			try {
				numCarta = new BigDecimal(request.getParameter("numeroCarta"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				response.sendRedirect("/errore.html");
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			if (request.getParameter("intestatario") == null) {
//				response.sendRedirect("/errore.html");
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			String intestatario = request.getParameter("intestatario");
			Date date;
			try {
				date = Date.valueOf(request.getParameter("dataScadenza"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			if (request.getParameter("codiceSicurezza").trim().length() != 3) {
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			Short codiceSicurezza;
			try {
				codiceSicurezza = Short.valueOf(request.getParameter("codiceSicurezza"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				response.sendRedirect("/errore.html");
				request.setAttribute("erroreC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
			Cliente cliente = new Cliente(idCliente);
			CartaCredito c = new CartaCredito();
			c.setCodiceSicurezza(codiceSicurezza);
			c.setIntestatario(intestatario);
			c.setNumeroCarta(numCarta);
			c.setDataScadenza(date);
			boolean creazione = cDAO.salva(c, cliente);
			// Il salva di cartacreditoDAO fa automaticamente l'inserimento nella join di
			// possiede ;-)
			if (creazione) {
				CartaCredito cartaCredito = cDAO.carteDelCliente(idCliente).get(0);
				idCarta = cartaCredito.getIdCartaCredito();
			} else {
				request.setAttribute("salvaC", true);
				request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
				return;
			}
		}
		// Creiamo la data di creazione dell'ordine
		Date dataCreazione = new Date(System.currentTimeMillis());
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Carrello carrello = carrelloDAO.getAttivo(idCliente);
		int idCarrello = carrello.getIdCarrello();
		MetodoSpedizioneDAO metodoSpedizioneDAO = new MetodoSpedizioneDAO();
		MetodoSpedizione metodoSpedizione = metodoSpedizioneDAO
				.getMetodoSpedizione(new MetodoSpedizione(Integer.parseInt(request.getParameter("radio"))));
		int idMetodoSpedizione = metodoSpedizione.getIdMetodoSpedizione();
		ArticoloComponeCarrelloDAO accDAO = new ArticoloComponeCarrelloDAO();
		Double costoTotale = accDAO.calcoloCosto(carrello) + metodoSpedizione.getSpeseSpedizione();
		OrdineDAO oDAO = new OrdineDAO();
		Ordine o = new Ordine(costoTotale, dataCreazione, idCarrello, idMetodoSpedizione, true, idIndirizzo);
		System.out.println("Ordine sdfjlsnejlf : " + o);
		if (oDAO.salva(o)) {
			PagamentoDAO pDAO = new PagamentoDAO();
			// Ci prendiamo l'ultimo ordine appena creato
			o = oDAO.getOrder(new Cliente(idCliente));
			double pagato = Math.random();
			if (pagato > 0.05) {
				if (pDAO.salva(new Pagamento(o.getNumeroOrdine(), dataCreazione, idCarta))) {
					response.sendRedirect("/privato/cliente/ordine/VisualizzaOrdini");
					return;
				}
			} else {
				pDAO.pagamentoFallito(o.getNumeroOrdine());
				response.sendRedirect("/privato/cliente/ordine/VisualizzaOrdini");
			}
		} else {
//			request.setAttribute("carrello", carrello);
			request.setAttribute("salvaO", true);
			System.out.println("Sono in dfouhseiofjho");
			request.getRequestDispatcher("/privato/cliente/ordine/RichiediCheckOut").forward(request, response);
			return;
		}

	}
//	
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(req, resp);
//	}
}
