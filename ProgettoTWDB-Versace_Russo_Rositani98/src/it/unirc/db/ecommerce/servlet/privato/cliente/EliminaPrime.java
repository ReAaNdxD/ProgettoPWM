package it.unirc.db.ecommerce.servlet.privato.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.ClientePrime;
import it.unirc.db.ecommerce.beans.ClientePrimeDAO;

/**
 * Servlet implementation class EliminaPrime
 */
@WebServlet("/privato/cliente/EliminaPrime")
public class EliminaPrime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaPrime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(request.getParameter("idPrime")==null || session.getAttribute("idCliente")==null) {
            response.sendRedirect("/login.jsp");
			return;
		}
        int idPrime = Integer.parseInt(request.getParameter("idPrime"));
        ClientePrimeDAO cpDAO = new ClientePrimeDAO();
        ClientePrime clientePrime = cpDAO.get(new ClientePrime(idPrime));
        if(cpDAO.elimina(clientePrime)) {
        	response.sendRedirect("/index.jsp");
        }else {
        	session.setAttribute("elimina", false);
        	response.sendRedirect("/WelcomeToPrime.jsp");
        }
        	
	}

}
