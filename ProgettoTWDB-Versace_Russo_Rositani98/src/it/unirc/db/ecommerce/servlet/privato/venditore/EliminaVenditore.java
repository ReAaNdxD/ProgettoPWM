package it.unirc.db.ecommerce.servlet.privato.venditore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;

/**
 * Servlet implementation class EliminaVenditore
 */
@WebServlet("/EliminaVenditore")
public class EliminaVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaVenditore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idVenditore=Integer.parseInt(request.getParameter("idVenditore"));
		Venditore venditore= new Venditore(idVenditore);
		VenditoreDAO venditoreDAO=new VenditoreDAO();
		boolean esito= venditoreDAO.elimina(venditore);
		if (esito) {
			HttpSession session= request.getSession();
			session.invalidate();
			response.sendRedirect("/");
			
		}
			
	}

}
