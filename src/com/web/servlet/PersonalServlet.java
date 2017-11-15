package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Reader;
import com.service.PersonalInforService;
import com.service.impl.PersonalInforImpl;

/**
 * Servlet implementation class PersonalServlet
 */
@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
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
		if (id == null) {
			response.getWriter().write("Please Sign in first! 5 seconds to jump to the search page");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/login.jsp");
		} else {
			PersonalInforService personalInforService = new PersonalInforImpl();
			Reader reader = personalInforService.PersonalInfor(id);
			if(reader!=null) {
				request.getSession().setAttribute("reader", reader);
				request.getRequestDispatcher("/PersonalInfor.jsp").forward(request, response);
			}else {
				response.getWriter().write("Query error! 5 seconds to jump to the search page");
				response.setHeader("refresh", "5;url=" + request.getContextPath() + "/reader_opertion.jsp");
			}
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
