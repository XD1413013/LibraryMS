package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.CancelReserveService;
import com.service.impl.CancelReserveImpl;

/**
 * Servlet implementation class CancelReserveServlet
 */
@WebServlet("/CancelReserveServlet")
public class CancelReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String book_id = request.getParameter("book_id");
		CancelReserveService crs = new CancelReserveImpl();
		if(crs.cancel(book_id)) {
			request.getRequestDispatcher("/reader_operation.jsp?result=yes").forward(request, response);
		}else {
			request.getRequestDispatcher("/reader_operation.jsp?result=no").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
