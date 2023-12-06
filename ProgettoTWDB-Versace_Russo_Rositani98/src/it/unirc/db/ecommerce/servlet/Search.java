package it.unirc.db.ecommerce.servlet;

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

import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.views.GridProduct;

/**
 * Servlet implementation class QueryResult
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
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
		System.out.println("queryString " + queryString);
		HashMap<String, Object> param = new HashMap<String, Object>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				System.out.println("i : " + i);
				String paramValue = paramValues[i];
				if (!(paramName.equals("search") || paramName.equals("currentPage")
						|| paramName.equals("recordsPerPage") || (paramName.equals("ref")))) {
					param.put(paramName, paramValue);
				}
			}
		}

//		if (queryString==null) {
//			response.sendRedirect("index3.jsp");
//			return;
//		}

		String ref = request.getParameter("ref");

		if ((queryString == null && ref == null)) {
			System.out.println("qui");
			response.sendRedirect("/");
			return;
		}

		if (param.isEmpty() && (queryString == null) && ref == null) {
			System.out.println("quo");
			response.sendRedirect("/");
			return;
		}

		if (queryString != null && queryString.length() == 0) {
			response.sendRedirect("/");
			return;
		}

		System.out.println("Dentro Search");

//		if (((queryString==null && ref==null) || ((param.isEmpty()&&queryString.equals("")) || queryString.equals("")))) {
//			response.sendRedirect("index3.jsp");
//			return;
//		}

		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
//		System.out.println("current page : " + currentPage);
		int recordsPerPage = 6;
		//recordsPerPage il numero di prodotti per pagina
//		if (request.getParameter("recordsPerPage")!=null) {
//			recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
//		}

		for (String i : param.keySet()) {
			System.out.println("Ciccio - " + i + "=" + param.get(i));
		}
		ArticoloDAO articoloDAO = new ArticoloDAO();
//		articoloDAO.getAllAvailableProducts(queryString, currentPage, recordsPerPage, param);
		Vector<GridProduct> queryResult = articoloDAO.getAllAvailableProducts(queryString, currentPage, recordsPerPage,
				param);
		Vector<String> brands = articoloDAO.getAllBrand(queryString, param);
		request.setAttribute("queryResult", queryResult);
		request.setAttribute("brands", brands);
		request.setAttribute("param", param);
		int rows = articoloDAO.getAllAvailableProductsRows(queryString, param);

		System.out.println("Rows : " + rows);

		int nOfPages = rows / recordsPerPage;

		if (nOfPages % recordsPerPage > 0) {

			nOfPages++;
		}

		request.setAttribute("noOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
//		request.setAttribute("recordsPerPage", recordsPerPage);

		RequestDispatcher dispatcher = request.getRequestDispatcher("shop-grid.jsp");
		dispatcher.forward(request, response);
	}

}
