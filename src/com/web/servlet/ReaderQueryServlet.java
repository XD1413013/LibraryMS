package com.web.servlet;

import com.dao.UserInformationDao;
import com.dao.impl.UserInformationDaoImpl;
import com.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReaderQueryServlet")
public class ReaderQueryServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().append("Served at: ").append(request.getContextPath());

        String reader_id = request.getParameter("reader_id");

        UserInformationDao ui = new UserInformationDaoImpl();
        Reader reader = ui.searchReader(reader_id);

        if (reader != null) {
            request.setAttribute("reader", reader);
            request.getRequestDispatcher("/readerQuery.jsp").forward(request, response);
        }else {
            response.getWriter().write("Did not successfully match any record! 5 seconds to jump to the search page");
            response.setHeader("refresh", "5;url=" + request.getContextPath()+ "/query.jsp");
        }
    }
}
