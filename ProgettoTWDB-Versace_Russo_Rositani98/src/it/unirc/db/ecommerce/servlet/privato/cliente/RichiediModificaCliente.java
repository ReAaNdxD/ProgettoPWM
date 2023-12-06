package it.unirc.db.ecommerce.servlet.privato.cliente;

import java.io.IOException;

import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClienteDAO;

/**
 * Servlet implementation class RichiediModificaCliente
 */
@WebServlet("/privato/cliente/RichiediModificaCliente")
public class RichiediModificaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RichiediModificaCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// QUando clicco il link nell'index invocherò questa Servlet che mi
		// reindirizzerà nella jsp modificaCliente
		HttpSession session = request.getSession();;
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		int idCliente = (int) session.getAttribute("idCliente");
		ClienteDAO cDAO = new ClienteDAO();
		Cliente c = cDAO.get(new Cliente(idCliente));
		request.setAttribute("cliente", c);
		request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
	}

}
