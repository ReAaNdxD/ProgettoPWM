package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;

/**
 * Servlet implementation class APISottocategoria
 */
@WebServlet("/privato/prodotto/APIProdotto3")
public class APIProdotto3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIProdotto3() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if ((session.getAttribute("Venditore") == null) && (session.getAttribute("Admin") == null)) {
			request.getRequestDispatcher("/loginVenditore.jsp").forward(request, response);
			return;
		}
		String sottocategoria = request.getParameter("subcategory");
		String testo=request.getParameter("charcater");
		System.out.println(sottocategoria);
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		String json = "";
		int idSottocategoria=0;
		if(sottocategoria!=null) {
			if(sottocategoria.equals(""))
				return;
			idSottocategoria=Integer.parseInt(sottocategoria);
			System.out.println(idSottocategoria);
			
		}else {
			System.out.println("giupeppe");
		}
		List<Prodotto> prodotti= prodottoDAO.cercaProdottoByNomeWhereSottocategoriaIs(testo, idSottocategoria);
		ObjectMapper mapper= new ObjectMapper();
		try {
			json= mapper.writeValueAsString(prodotti);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		PrintWriter pw= response.getWriter();
		pw.print(json.toString());
		pw.close();
	}

}
