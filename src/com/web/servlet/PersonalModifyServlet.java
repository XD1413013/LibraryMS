package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.PersonalInforService;
import com.service.impl.PersonalInforImpl;

/**
 * Servlet implementation class PersonalModifyServlet
 */
@WebServlet("/PersonalModifyServlet")
public class PersonalModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String reader_id = (String) request.getSession().getAttribute("reader_id");
		String pwd = request.getParameter("pwd");
		String c_pwd = request.getParameter("c_pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("telephone");
		PersonalInforService personalInforService = new PersonalInforImpl();
		if(pwd!=null && c_pwd!=null && pwd.equals(c_pwd)) {
			if(!personalInforService.Modify(reader_id, pwd, 0, 1)) {
				request.getRequestDispatcher("/ModifyPwd.jsp?remind=no").forward(request, response);
			}else {
				request.getSession().setAttribute("pwd",null);
				request.getSession().setAttribute("c_pwd",null);
				response.getWriter().write("   successfully ! 5 seconds to jump to the login page");
				response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/login.jsp");
			}
		}else if(pwd!=null && c_pwd!=null && !pwd.equals(c_pwd)) {
			request.getRequestDispatcher("/ModifyPwd.jsp?remind=no").forward(request, response);
		}
		
		if(email!=null) {
			if(!personalInforService.Modify(reader_id, email, 1, 1)) {
				request.getRequestDispatcher("/Modifyemail.jsp?remind=no").forward(request, response);
			}else {
				request.getSession().setAttribute("email",null);
				response.getWriter().write("   successfully ! 5 seconds to jump to the search page");
				response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/search.jsp");
			}
		}
		
		if(phone!=null) {
			if(!personalInforService.Modify(reader_id, phone, 2, 1)) {
				request.getRequestDispatcher("/Modifyphone.jsp?remind=no").forward(request, response);
			}else {
				request.getSession().setAttribute("phone",null);
				response.getWriter().write("   successfully ! 5 seconds to jump to the search page");
				response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/search.jsp");
			}
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
