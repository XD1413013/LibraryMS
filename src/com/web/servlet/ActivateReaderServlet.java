package com.web.servlet;

import com.service.ReaderService;
import com.service.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ActivateReaderServlet")
public class ActivateReaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("Served at: ").append(request.getContextPath());

        String id = request.getParameter("id");
        ReaderService readerService = new ReaderServiceImpl();

        if (readerService.activateReader(id)) {
            request.setAttribute("state", 1);
            response.getWriter().write("Added successfully! Jumping after 5 seconds");
            response.setHeader("refresh", "5;url=" + request.getContextPath() + "/add.jsp");
        } else {
            response.getWriter().write("No Such User! Jumping after 5 seconds");
            response.setHeader("refresh", "5;url=" + request.getContextPath() + "/activateReader.jsp");
        }
    }
}
