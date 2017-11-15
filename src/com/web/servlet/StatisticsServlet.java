package com.web.servlet;

import com.service.StatisticsService;
import com.service.impl.StatisticsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("Served at: ").append(request.getContextPath());

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime") + " 24:0:0";

        StatisticsService statisticsService = new StatisticsServiceImpl();

        request.setAttribute("income", statisticsService.calcuPayment(startTime, endTime));
        request.getRequestDispatcher("showIncome.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
