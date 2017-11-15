package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.CheckbookService;
import com.service.impl.CheckbookServiceImpl;
import com.service.impl.LoginServiceImpl;
import com.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private LoginService loginService = new LoginServiceImpl();
	private CheckbookService checkbook = new CheckbookServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String id = request.getParameter("id");
		String psw = request.getParameter("pwd");
		String isLibrarian = request.getParameter("isLibrarian");
		String status = loginService.check(id, psw, isLibrarian);

		if (status.equals("1")) {
			if (isLibrarian.equals("true")) {
				request.getSession().setAttribute("librarian_id", id);
				request.getRequestDispatcher("/index2.jsp").forward(request, response);
			}
			else {
				request.getSession().setAttribute("reader_id", id);
				boolean whetherRemind = checkbook.checkbook(id);
				if (whetherRemind){
					request.getRequestDispatcher("/search.jsp?remind=yes").forward(request, response);
				}else {
				request.getRequestDispatcher("/search.jsp").forward(request, response);}
			}
		}
		else if(status.equals("0")){
			response.sendRedirect("login.jsp?error=yes");
		}else {
			response.sendRedirect("login.jsp?error=yes1");
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
