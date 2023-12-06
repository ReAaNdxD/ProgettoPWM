package it.unirc.db.ecommerce.servlet.privato.cliente.ordine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unirc.db.ecommerce.beans.MetodoSpedizione;
import it.unirc.db.ecommerce.beans.MetodoSpedizioneDAO;
import it.unirc.db.ecommerce.beans.Prodotto;
import it.unirc.db.ecommerce.beans.ProdottoDAO;

/**
 * Servlet implementation class CalcoloSpedizione
 */
@WebServlet("/privato/cliente/ordine/CalcoloSpedizione")
public class CalcoloSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalcoloSpedizione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MetodoSpedizioneDAO metodoSpedizioneDAO = new MetodoSpedizioneDAO();
		String json = "";
		List<MetodoSpedizione> metodi = metodoSpedizioneDAO.getAllMetodoSpedizione();
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(metodi);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		pw.print(json.toString());
		pw.close();

	}


}