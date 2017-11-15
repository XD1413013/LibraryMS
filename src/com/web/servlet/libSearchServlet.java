package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/libSearchServlet")
public class libSearchServlet extends HttpServlet {
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
		// 获取表单数据
		String input = "";
		String message = null;
		try {
			input = request.getParameter("bookInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 处理业务逻辑
		BookService bs = new BookServiceImpl();
		List<Book> bl = null;
		try {
			bl = bs.search(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 分发转向
		if (bl != null) {
			request.setAttribute("bookList", bl);
			request.getRequestDispatcher("/liblist.jsp").forward(request, response);
		}else {
			message="Did not successfully match any record !";
			request.setAttribute("message", message);
			String librarian_id = (String) request.getSession().getAttribute("librarian_id");
			if(librarian_id==null) {
				message="Please login first !";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/index2.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
