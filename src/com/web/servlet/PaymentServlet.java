package com.web.servlet;

import java.io.IOException;
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
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
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

		double payment = Double.parseDouble(request.getParameter("payment"));
		String book_id = (String) request.getSession().getAttribute("book_id");
		double money = (double) request.getSession().getAttribute("money");
		String borrow_time = (String) request.getSession().getAttribute("borrow_time");

		String status = (String)request.getSession().getAttribute("pay_status");
		String name = (String) request.getSession().getAttribute("librarian_id");
		String message = null;
		if (name != null) {

			if (payment == money) {
				new BorrowInforImpl().addIncome(payment);
				BorrowService bs = new BorrowServiceImpl();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				String now_ = sdf.format(now);
				BookService bd = new BookServiceImpl();
				if (status.equals("return")){
					if (bd.returnBook(book_id,now_,money)) {
						message="successfully ! ";
						request.setAttribute("message", message);
					} else {
						message="failed !";
						request.setAttribute("message", message);
					}
				}
				else if (status.equals("borrow")){
					String reader_id = (String)request.getSession().getAttribute("reader_id");
					new BorrowInforImpl().fresh(reader_id);
					message="pay successfully!";
					request.setAttribute("message", message);
					request.getRequestDispatcher("/index2.jsp").forward(request, response);
				}
			} else {
				response.getWriter().write("Please enter the correct amount of money! Jumping after 5 seconds");
				response.setHeader("refresh", "5;url=" + request.getContextPath() + "/payment.jsp");
			}
			request.getRequestDispatcher("/index2.jsp").forward(request, response);
		}else {
			message="Pay failed! please log in first";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
