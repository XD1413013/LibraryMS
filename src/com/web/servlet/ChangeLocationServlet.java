package com.web.servlet;

import com.domain.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeLocationServlet")
public class ChangeLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("Served at: ").append(request.getContextPath());

        String book_id = request.getParameter("book_id");
        String location = request.getParameter("location");
        Book book = new Book();
        book.setBook_id(book_id);
        book.setLocation(location);

        BookService bs = new BookServiceImpl();
        String message = null;
        if(bs.changeLocation(book)) {
            message = "Change Successfully!";
        } else {
            message = "Change Failed!";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("changeLocation.jsp").forward(request, response);
    }
}
