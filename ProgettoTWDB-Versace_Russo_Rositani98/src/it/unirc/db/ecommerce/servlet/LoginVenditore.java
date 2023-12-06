package it.unirc.db.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.Admin;
import it.unirc.db.ecommerce.beans.AdminDAO;
import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.beans.VenditoreDAO;
import it.unirc.db.ecommerce.utils.Utils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginVenditore")
public class LoginVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginVenditore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Venditore venditore = new Venditore(email, password);
		VenditoreDAO vDAO = new VenditoreDAO();
	
		if (Utils.isEmailValid(email)) {
			System.out.println("email valida");
			if (vDAO.login(venditore) != null) {
				System.out.println("loggo");
				HttpSession session = request.getSession();
				Venditore venditore2= vDAO.login(venditore);
				System.out.println(venditore2);
				session.setAttribute("Venditore", venditore2);
				System.out.println(session.getAttribute("Venditore"));
				if(request.getParameter("checkbox1")!=null) {
					Cookie cookie= new Cookie("emailDAGVenditore", email);
					cookie.setMaxAge(60*60*24);
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
				}
				response.sendRedirect("/privato/prodotto/VisualizzaProdottiInVendita");
				return;

			} else {
				request.setAttribute("errore", true);
				request.getRequestDispatcher("loginVenditore.jsp").forward(request, response);
				return;
			}
		}else {
			System.out.println("email non valida");
			Admin admin= new Admin(email,password);
			AdminDAO adminDAO= new AdminDAO();
			if(adminDAO.login(admin)!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("Admin", admin);
				request.getRequestDispatcher("/privato/prodotto/VisualizzaProdottiInVendita").forward(request, response);
				return;
			}else {
				request.setAttribute("erroreEmail", true);
				request.getRequestDispatcher("loginVenditore.jsp").forward(request, response);
				return;
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			doPost(request,response);
		
	}
}