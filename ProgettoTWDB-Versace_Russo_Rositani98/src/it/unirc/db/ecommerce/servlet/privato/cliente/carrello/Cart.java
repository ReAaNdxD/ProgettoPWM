package it.unirc.db.ecommerce.servlet.privato.cliente.carrello;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.beans.Carrello;
import it.unirc.db.ecommerce.beans.CarrelloDAO;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO;
import it.unirc.db.ecommerce.views.ViewProduct;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/privato/cliente/carrello/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		private void verify() {
		// TODO Auto-generated method stub
		// Verifica quantità = 0 o > della qtà disponibile
		HttpSession session = request.getSession();
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
		ArticoloDAO articoloDAO = new ArticoloDAO();
		Cliente cliente = new Cliente((int) session.getAttribute("idCliente"));
		Vector<ViewProduct> viewProducts = articoloDAO.getAllArticoliCliente(cliente);
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Carrello cart = carrelloDAO.getCarrelloByCliente(cliente);
		if (viewProducts != null) {
			for (ViewProduct viewProduct : viewProducts) {
				System.out.println("Dentro il for");
				Articolo a = articoloDAO.get(new Articolo(viewProduct.getIdArticolo()));
				if (viewProduct.getDisponibilita() == 0) {
					componeDAO.elimina(a, cart);

					System.out.println("Ho eliminato : " + a);

				} else if (viewProduct.getQuantita() > viewProduct.getDisponibilita()) {

					System.out.println("Ho modificato : " + a);
					componeDAO.modifica(a, cart, viewProduct.getDisponibilita());
				}

				componeDAO.aggiornaPrezzo(a, cart);
			}
		}

		viewProducts = articoloDAO.getAllArticoliCliente(cliente);

//		for (ViewProduct viewProduct : viewProducts) {
//			System.out.println(viewProduct);
//		}

		request.setAttribute("viewProducts", viewProducts);
//		}

//		ServletContext sc = getServletContext();

//		if (sc!=null) {
//			if (sc.getAttribute("msg") != null && sc.getAttribute("success") != null) {
//				System.out.println("Ci sono");
//				sc.setAttribute("msg", sc.getAttribute("msg"));
//				sc.setAttribute("success", sc.getAttribute("success"));
//			}
//					if(request.getAttribute("msg")!=null&&request.getAttribute("success")!=null) {
//						request.setAttribute("msg", request.getAttribute("msg"));
//						request.setAttribute("success", request.getAttribute("success"));
//					}
//		}
		request.getRequestDispatcher("/WEB-INF/privato/cliente/carrello/cart.jsp").forward(request, response);

	}

}
