package it.unirc.db.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.views.GridProduct;

/**
 * Servlet implementation class ProductDetails
 */
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
//		Cookie[] cookies = request.getCookies();
//		String[] spl=null;
//		
//		if (cookies!=null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("nome")) {
//					String aString = cookie.getValue()+"-Martio2";
//					
//					spl = aString.replaceAll("\\s+","").split("-");
//					
//					System.out.println(aString);
//					Cookie cooki = new Cookie("nome", aString);
//					cooki.setMaxAge(60*60*24*365);
//					response.addCookie(cooki);
//				}
//			}
//			System.out.println("daonfiow");
//			for (String string : spl) {
//				System.out.println("Array "+string);
//			}
//			
//		}
		
		
		
		// INPUT 
		int productID =Integer.parseInt(request.getParameter("id"));
		
		
		ArticoloDAO articoloDAO = new ArticoloDAO();
		GridProduct a=articoloDAO.getProduct(new Articolo(productID));
		
		request.setAttribute("product", a);
		
		request.getRequestDispatcher("product-detail.jsp").forward(request, response);
	}

}
