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
        Book book = null;

        GetBookInfoService getBookInfoService = new GetBookInfoServiceImpl();
		BookService bookServiceImpl = new BookServiceImpl();

		book = getBookInfoService.getBookFromDouBan(isbn);

		if (book == null) {
            response.getWriter().write("No Such Book! Jumping after 5 seconds");
            response.setHeader("refresh", "5;url=" + request.getContextPath() + "/addBook.jsp");
        } else {
            book.setLocation(location);
            book.setBook_id(id);

            if (bookServiceImpl.addBook(book)) {
                request.setAttribute("state", 1);
                response.getWriter().write("Added successfully! Jumping after 5 seconds");
                response.setHeader("refresh", "5;url=" + request.getContextPath() + "/add.jsp");
            } else {
                response.getWriter().write("Add failed! Jumping after 5 seconds");
                response.setHeader("refresh", "5;url=" + request.getContextPath() + "/addBook.jsp");
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
