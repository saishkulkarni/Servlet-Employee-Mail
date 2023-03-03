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

@WebServlet("/delete")
public class Delete extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	EmployeeDao dao=new EmployeeDao();
	
	dao.remove(dao.find(id));
	
	List<Employee> list = dao.fetchAll();
	if (list.isEmpty()) {
		resp.getWriter().print("<h1>No Data Present </h1>");
		req.getRequestDispatcher("index.jsp").include(req, resp);
	} else {
		resp.getWriter().print("<h1>Data Deleted Successfully</h1>");
		req.getSession().setAttribute("list", list);
		resp.sendRedirect("fetchall.jsp");
	}
	
}
}
