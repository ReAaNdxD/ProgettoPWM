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

import it.unirc.db.ecommerce.beans.Categoria;
import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;
import it.unirc.db.ecommerce.beans.Sottocategoria;
import it.unirc.db.ecommerce.beans.SottocategoriaDAO;

/**
 * Servlet implementation class APISottocategoria
 */
@WebServlet("/privato/prodotto/APIProdotto4")
public class APIProdotto4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIProdotto4() {
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
			response.sendRedirect("/RichiediLoginVenditore");
			return;
		}
		String categoria = request.getParameter("category");
		String testo=request.getParameter("charcater");
		System.out.println(categoria);
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		String json = "";
		int idCategoria=0;
		if(categoria!=null) {
			if(categoria.equals(""))
				return;
			idCategoria=Integer.parseInt(categoria);
			System.out.println(idCategoria);
			
		}else {
			System.out.println("giupeppe");
		}
		List<Prodotto> prodotti= prodottoDAO.cercaProdottoByNomeWhereCategoriaIs(testo, idCategoria);
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
