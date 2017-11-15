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
@WebServlet("/libPersonalModifyServlet")
public class libPersonalModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String librarian_id = (String) request.getSession().getAttribute("librarian_id");
		String pwd = request.getParameter("pwd");
		String c_pwd = request.getParameter("c_pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("telephone");
		PersonalInforService personalInforService = new PersonalInforImpl();
		if(pwd!=null && c_pwd!=null && pwd.equals(c_pwd)) {
			if(!personalInforService.Modify(librarian_id, pwd, 0, 0)) {
				request.getRequestDispatcher("/lib_ModifyPwd.jsp?remind4=no").forward(request, response);
			}else {
				request.getSession().setAttribute("pwd",null);
				request.getSession().setAttribute("c_pwd",null);
				response.getWriter().write("   successfully ! 5 seconds to jump");
				response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/login.jsp");
			}
		}else if(pwd!=null && c_pwd!=null &&!pwd.equals(c_pwd)) {
			request.getRequestDispatcher("/lib_ModifyPwd.jsp?remind4=no").forward(request, response);
		}
		
		if(email!=null) {
			if(!personalInforService.Modify(librarian_id, email, 1, 0)) {
				request.getRequestDispatcher("/lib_Modifyemail.jsp?remind5=no").forward(request, response);
			}else {
				request.getSession().setAttribute("email",null);
				response.getWriter().write("   successfully ! 5 seconds to jump ");
				response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/index2.jsp");
			}
		}
		
		if(phone!=null) {
			if(!personalInforService.Modify(librarian_id, phone, 2, 0)) {
				request.getRequestDispatcher("/lib_Modifyphone.jsp?remind6=no").forward(request, response);
			}else {
				request.getSession().setAttribute("phone",null);
				response.getWriter().write("   successfully ! 5 seconds to jump");
				response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/index2.jsp");
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
