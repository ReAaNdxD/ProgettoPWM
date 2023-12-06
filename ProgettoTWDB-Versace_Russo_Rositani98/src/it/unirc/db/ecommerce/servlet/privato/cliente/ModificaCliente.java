package it.unirc.db.ecommerce.servlet.privato.cliente;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClienteDAO;

/**
 * Servlet implementation class ModificaCliente
 */
@WebServlet("/privato/cliente/ModificaCliente")
public class ModificaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("idCliente") == null) {
			// Non ha fatto il login
			response.sendRedirect("/login.jsp");
			return;
		}
		int idCliente = (int) session.getAttribute("idCliente");
		ClienteDAO cDAO = new ClienteDAO();
		Cliente c = new Cliente(idCliente);
		 c = cDAO.get(c);
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");

		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("password");
		String verificaPassword = request.getParameter("verificaPassword");

		if (nome.replaceAll("\\s", "") == "" || !nome.replaceAll("\\s", "").matches("[a-zA-Z]+")) {
			// E' vuota o contiene numeri
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("nome");
			return;
		}
		String nomeCliente = c.getNome();
		if(!nome.startsWith(nomeCliente)) {
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("nomeCliente");
			return;
		}
		
		if (cognome.replaceAll("\\s", "") == "" || !cognome.replaceAll("\\s", "").matches("[a-zA-Z]+")) {
			// E' vuota o contiene numeri
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("cognome");
			return;
		}
		try {
//			java.util.Date  data =new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascita"));
			Date data = Date.valueOf(request.getParameter("dataNascita"));
			c.setDataNascita(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("data");
			return;
		}
		if (email.replaceAll("\\s", "") == "" || !email.replaceAll("\\s", "").matches(
				"^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
			// E' vuota o non è un email valida
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("email");
			return;
		}
		if (password.replaceAll("\\s", "") == "" || !password.replaceAll("\\s", "")
				.matches("^(?=.*[0-9])(?=.*[!@#$%^&*.])[a-zA-Z0-9!@#$%^&*.]{7,15}$")) {
			// E' vuota o la password non è valida
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("passw");
			return;
		}
		if (!password.equals(verificaPassword)) {
			// Le password non coincidono
			request.setAttribute("errore", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("vhevkpassw");
			System.out.println(password);
			System.out.println(verificaPassword);
			return;
		}
		// Controllo se l'email già esiste
		if (cDAO.checkEmailForUpdate(email, idCliente)) {
			// Esiste già l'email quindi lo devo rimandare alla jsp per fargli inserire un'
			// altra email
			request.setAttribute("email", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("check email");
			return;
		}
		c.setNome(nome);
		c.setCognome(cognome);
		c.setEmail(email);
		c.setPassword(password);
		// Provo a salvare il nuovo cliente essendo che ha superato tutti i check
		if (!cDAO.modifica(c)) {
			request.setAttribute("modifica", true);
			request.getRequestDispatcher("modificaCliente.jsp").forward(request, response);
			System.out.println("modifica");
			return;
		} // L'ha modificato lo mando nell'index
		response.sendRedirect("/");
	}
}
