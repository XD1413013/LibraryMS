package com.web.servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Book;
import com.service.BookService;
import com.service.GetBookInfoService;
import com.service.impl.BookServiceImpl;
import com.service.impl.GetBookInfoServiceImpl;
import com.utils.DouBan;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
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

		String location = request.getParameter("location");
        String isbn = request.getParameter("isbn");
        String id = new Date().getTime()%(24 * 60 * 60 * 1000) + "";

        GetBookInfoService getBookInfoService = new GetBookInfoServiceImpl();
		BookService bookServiceImpl = new BookServiceImpl();

		Book book = getBookInfoService.getBookFromDouBan(isbn);
		String message = null;

		if (book == null) {
            message = "No Such Book!";
        } else {
            book.setLocation(location);
            book.setBook_id(id);

            if (bookServiceImpl.addBook(book)) {
                message = "Added successfully! Id is: " + id;
            } else {
                message = "Add failed!";
            }
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("addBook.jsp").forward(request, response);
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
