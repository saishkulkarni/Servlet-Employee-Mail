package employee_mail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee_mail.dao.EmployeeDao;
import employee_mail.dto.Employee;

@WebServlet("/fetchAll")
public class FetchAll extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeDao dao = new EmployeeDao();
		List<Employee> list = dao.fetchAll();
		if (list.isEmpty()) {
			resp.getWriter().print("<h1>No Data Present </h1>");
			req.getRequestDispatcher("index.jsp").include(req, resp);
		} else {
			req.getSession().setAttribute("list", list);
			resp.sendRedirect("fetchall.jsp");
		}
	}
}
