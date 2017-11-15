package com.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.BorrowHistoryDaoImpl;
import com.dao.impl.BorrowInforImpl;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.service.impl.BorrowServiceImpl;

/**
 * Servlet implementation class BorrowInforServlet
 */
@WebServlet("/BorrowInforServlet")
public class BorrowInforServlet extends HttpServlet {
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

		String reader_id = request.getParameter("reader_id");
		String book_id = request.getParameter("book_id");

		BookService bd = new BookServiceImpl();
		BookDao bb = new BookDaoImpl();
		String message = null;
		if(reader_id.equals("") || book_id.equals("")) {
			message = "Please enter correct information!";
			request.setAttribute("message", message);
	        request.getRequestDispatcher("/borrow.jsp").forward(request, response);
			return;
		}else {
			boolean flag = bb.isReader(reader_id);
			if (!flag){
				request.setAttribute("state", 0);
				message = "Please actvite to be a reader first!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/index2.jsp").forward(request, response);
				return;
			}
		}


		String name = (String) request.getSession().getAttribute("librarian_id");

		if (name != null) {

			boolean remind = new BorrowHistoryDaoImpl().whetherRemind(reader_id);
			

			if (remind){
				double payment = new BorrowInforImpl().prePay(reader_id);
				try {
				int days = (int)payment;

				if (payment != 0 ){
					request.getSession().setAttribute("money", payment);
					request.setAttribute("days",days);
					request.getSession().setAttribute("pay_status","borrow");
					request.getSession().setAttribute("reader_id",reader_id);
					request.getRequestDispatcher("/payment.jsp").forward(request, response);
				}
				else{
					try {
						String state = bd.borrow(book_id, reader_id);
						if (state.equals("Success")) {
							request.setAttribute("state", 1);
							message = "Borrow successfully! ";
							request.setAttribute("message", message);
					        request.getRequestDispatcher("/index2.jsp").forward(request, response);
							return;
						}
						else {
							request.setAttribute("state", 0);
							message = state;
							request.setAttribute("message", message);
					        request.getRequestDispatcher("/index2.jsp").forward(request, response);
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}catch(Exception e) {
				}
			}
			else{
				try {
					String state = bd.borrow(book_id, reader_id);
					if (state.equals("Success")) {
						request.setAttribute("state", 1);
						message = "Borrow successfully! ";
						request.setAttribute("message", message);
				        request.getRequestDispatcher("/index2.jsp").forward(request, response);
						return;
					}
					else {
						request.setAttribute("state", 0);
						message = state;
						request.setAttribute("message", message);
				        request.getRequestDispatcher("/index2.jsp").forward(request, response);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else {
			message = "Borrow failed! please log in first!";
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
