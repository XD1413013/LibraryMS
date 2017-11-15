package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Librarian;
import com.service.PersonalInforService;
import com.service.impl.PersonalInforImpl;

/**
 * Servlet implementation class libPersonalServlet
 */
@WebServlet("/libPersonalServlet")
public class libPersonalServlet extends HttpServlet {
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
		String message = null;
		String id = (String) request.getSession().getAttribute("librarian_id");
		if (id == null) {
			message="Please Sign in first!";
			request.setAttribute("message", message);
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			PersonalInforService personalInforService = new PersonalInforImpl();
			Librarian librarian = personalInforService.libPersonalInfor(id);
			if(librarian!=null) {
				request.getSession().setAttribute("librarian", librarian);
				request.getRequestDispatcher("/lib_PersonalInfor.jsp").forward(request, response);
			}else {
				message="Query error !";
				request.setAttribute("message", message);
		        request.getRequestDispatcher("/index2.jsp").forward(request, response);
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
