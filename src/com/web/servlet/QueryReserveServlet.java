package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Book;
import com.service.ViewReserve;
import com.service.impl.ViewReserveImpl;

/**
 * Servlet implementation class QueryReserveServlet
 */
@WebServlet("/QueryReserveServlet")
public class QueryReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String id = (String) request.getSession().getAttribute("reader_id");
		
		ViewReserve reserve = new ViewReserveImpl();
		List<Book> books = reserve.view(id);
		if(books.size()>0) {
			request.getSession().setAttribute("books",books);
			request.getRequestDispatcher("/reader_operation.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/reader_operation.jsp?re=no").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
