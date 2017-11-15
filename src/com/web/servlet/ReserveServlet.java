package com.web.servlet;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().append("Served at: ").append(request.getContextPath());
        String book_isbn = request.getParameter("book_isbn");
        int number = Integer.parseInt(request.getParameter("num"));
        String reader_id = request.getParameter("user_id");

        BookDao bb = new BookDaoImpl();
        boolean flag = bb.isReader(reader_id);
        if (!flag){
            response.getWriter().write("Please actvite to be a reader first! Jumping after 5 seconds");
            response.setHeader("refresh", "5;url=" + request.getContextPath() + "/search.jsp");
            return;
        }

        if (number == 0) {
            request.getRequestDispatcher("/reader_operation.jsp?reserve=no").forward(request, response);
        }
        else {
            String state = bb.checkReserveNum(reader_id);
            if (state.equals("R_yes")){
               boolean fl =  bb.reserveBook(book_isbn,reader_id);
               if (fl){
                   request.getRequestDispatcher("/reader_operation.jsp?reserve=yes").forward(request, response);
               }
               else{
                   request.getRequestDispatcher("/reader_operation.jsp?reserve=no2").forward(request, response);
               }
            }
            else {
                request.getRequestDispatcher("/reader_operation.jsp?reserve=no3").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
