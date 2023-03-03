package employee_mail.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import employee_mail.dao.EmployeeDao;
import employee_mail.dto.Employee;

@WebServlet("/save")
@MultipartConfig
public class SaveEmployee extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Employee employee=new Employee();
	
	employee.setName(req.getParameter("name"));
	employee.setMobile(Long.parseLong(req.getParameter("mobile")));
	employee.setEmail(req.getParameter("email"));
	
	byte[] pic = null;
	Part filepart = req.getPart("resume");
	if (filepart != null) {
		InputStream inputStream = filepart.getInputStream();
		pic = new byte[inputStream.available()];
		inputStream.read(pic);
	}

	employee.setResume(pic);
	
	EmployeeDao dao=new EmployeeDao();
	
	dao.sendEmail(employee.getEmail());
	dao.save(employee);
	
	resp.getWriter().print("<html><body><h2>Data Inserted Successfully and mail sent</h1></body></html>");
	req.getRequestDispatcher("front.html").include(req, resp);
}
}
