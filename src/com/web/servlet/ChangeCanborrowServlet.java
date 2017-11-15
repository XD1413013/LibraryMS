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

@WebServlet("/ChangeCanBorrowServlet")
public class ChangeCanborrowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("served at: ").append(request.getContextPath());

        String book_id = request.getParameter("book_id");
        String can_borrow = request.getParameter("can_borrow");
        Book book = new Book();
        book.setBook_id(book_id);
        book.setCan_borrow(can_borrow);

        BookService bs = new BookServiceImpl();
        if(bs.changeCanBorrow(book)) {
            response.getWriter().write("Change Successfully! Jumping after 5 seconds!");
            response.setHeader("refresh", "5;url=" + request.getContextPath() + "/change.jsp");
        } else {
            response.getWriter().write("Change Failed! Jumping after 5 seconds!");
            response.setHeader("refresh", "5;url=" + request.getContextPath() + "/changeCanBorrow.jsp");
        }
    }
}
