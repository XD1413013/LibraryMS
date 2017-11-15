package com.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.impl.BorrowInforImpl;
import com.service.BookService;
import com.service.BorrowService;
import com.service.impl.BookServiceImpl;
import com.service.impl.BorrowServiceImpl;

/**
 * Servlet implementation class ReturnInforServlet
 */
@WebServlet("/ReturnInforServlet")
public class ReturnInforServlet extends HttpServlet {
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

		String book_id = request.getParameter("book_id");

		String name = (String) request.getSession().getAttribute("librarian_id");
		if (name != null) {
			BorrowService bs = new BorrowServiceImpl();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String now_ = sdf.format(now);
			try {
				now = sdf.parse(now_);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date date = (bs.searchInfor(book_id));
			if (date != null) {
				long days = now.getTime() / (24 * 60 * 60 * 1000) - date.getTime() / (24 * 60 * 60 * 1000);
				double money = 0;
				if (days > 30) {
					money = days - 30;
					money -= new BorrowInforImpl().getMoney(book_id);
					request.setAttribute("days", days - 30);
					request.getSession().setAttribute("money", money);
					request.getSession().setAttribute("book_id", book_id);
					request.getSession().setAttribute("borrow_time", sdf.format(date));
					request.getSession().setAttribute("pay_status","return");
					request.getRequestDispatcher("/payment.jsp").forward(request, response);
				}
				else {
					BookService bd = new BookServiceImpl();
					if (bd.returnBook(book_id,now_,money)) {
						response.getWriter().write("Return successfully! Jumping after 5 seconds!");
					}
					else {
						response.getWriter().write("Return failed!");
					}
				}
			} else {
				response.getWriter().write("Return failed!");
			}
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/index2.jsp");
		}else {
			response.getWriter().write("Add failed! please log in first");
			response.setHeader("refresh", "5;url=" + request.getContextPath() + "/login.jsp");
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
