package it.unirc.db.ecommerce.servlet.privato.prodotto;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.beans.Venditore;
import it.unirc.db.ecommerce.views.GridProduct;

/**
 * Servlet implementation class QueryResult
 */
@WebServlet({ "/QueryResult", "/privato/prodotto/QueryResult" })
public class QueryResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String queryString = request.getParameter("search");
		HttpSession session= request.getSession();
		if(session.getAttribute("Prodotto")!=null) {
			session.setAttribute("Prodotto",null);
		}
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// System.out.println("current page : " + currentPage);
		int recordsPerPage = 30;
		// if (request.getParameter("recordsPerPage")!=null) {
		// recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
		// }

		ArticoloDAO articoloDAO = new ArticoloDAO();

		HashMap<String, Object> param = new HashMap<String, Object>();
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				if (!(paramName.equals("search") || paramName.equals("currentPage")
						|| paramName.equals("recordsPerPage"))) {
					param.put(paramName, paramValue);
				}
			}
		}
		if(session.getAttribute("Venditore")!=null) {
		Venditore venditore=(Venditore)session.getAttribute("Venditore");
		param.put("venditore", venditore);
		}else if(session.getAttribute("Admin")!=null) {
			Venditore venditore=new Venditore(1);
			param.put("venditore", venditore);
		}
		for (String i : param.keySet()) {
			System.out.println(i + "=" + param.get(i));
		}
		Vector<GridProduct> queryResult = articoloDAO.getAllAvailableProducts(queryString, currentPage, recordsPerPage,
				param);
		request.setAttribute("queryResult", queryResult);
		int rows = articoloDAO.getAllAvailableProductsRows(queryString,param);
		System.out.println("Rows : " + rows);
		int nOfPages = rows / recordsPerPage;
		if (nOfPages % recordsPerPage > 0) {
			nOfPages++;
		}
		request.setAttribute("noOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/privato/prodotto/VisualizzaProdottiInVendita");
		dispatcher.forward(request, response);
	}

}