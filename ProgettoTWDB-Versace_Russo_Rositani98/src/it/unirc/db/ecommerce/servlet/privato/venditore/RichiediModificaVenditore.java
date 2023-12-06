package it.unirc.db.ecommerce.servlet.privato.venditore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;




/**
 * Servlet implementation class RichiediModificaVenditore
 */
@WebServlet("/privato/venditore/RichiediModificaVenditore")
public class RichiediModificaVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediModificaVenditore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Venditore venditore=(Venditore) session.getAttribute("Venditore");
		request.getRequestDispatcher("modificaVenditore.jsp").forward(request, response);
		
	}

}
