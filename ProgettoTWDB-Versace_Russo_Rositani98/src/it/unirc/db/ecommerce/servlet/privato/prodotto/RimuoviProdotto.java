package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.sun.java.swing.plaf.windows.resources.windows;

import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;
import javafx.scene.control.Alert;

/**
 * Servlet implementation class RimuoviProdotto
 */
@WebServlet("/privato/prodotto/RimuoviProdotto")
public class RimuoviProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		if ((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
		
		int idProdotto= Integer.parseInt(request.getParameter("idProdotto"));
		ProdottoDAO prodottoDAO= new ProdottoDAO();
		boolean errore=true;
		if(prodottoDAO.elimina(new Prodotto(idProdotto))) {
			response.sendRedirect("VisualizzaProdotto");
			return;
		}else {
			System.out.println("Non lo elimina perchè è un articolo");
			request.setAttribute("erroreProdotto", errore);
		    request.getRequestDispatcher("VisualizzaProdotto").forward(request, response);
		    return;
		}
	
	}

}
