<%@page import="employee_mail.dto.Employee"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fetch All</title>
</head>
<body>
	<%
	List<Employee> list = (List<Employee>) session.getAttribute("list");
	%>

	<h1>List Of All Employees</h1>
	<table border="1">
		<tr>
			<th>Full Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Resume</th>
			<th>Delete</th>
		</tr>
		<%
		for (Employee employee : list) {
		%>
		<tr>
			<td><%=employee.getName()%></td>
			<td><%=employee.getEmail()%></td>
			<td><%=employee.getMobile()%></td>
			<td>
			<%
		     String base64 = Base64.encodeBase64String(employee.getResume()); 
			%>
			<iframe src="data:application/pdf;base64,<%=base64%>" width="100%" height="500"></iframe>
			</td>
			<td><a href="delete?id=<%=employee.getId()%>"><button>Delete</button></a></td>
			
	</tr>

		<%
		}
		%>

	</table>



</body>
</html>