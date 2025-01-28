package mypackage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

import jakarta.servlet.annotation.*;

@WebServlet("/Voting")
public class Voting extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		
		LocalDate birthdate = LocalDate.parse(dob);
		LocalDate currentdate = LocalDate.now();
		
		int age = Period.between(birthdate, currentdate).getYears();
		
		if (age >= 18) {
			out.println("<table border = 1><tr><th>Name</th><th>Email</th><th>Age</th><th>Eligiblity</th></tr>");
			out.println("<tr><td>" + name + "</td><td>" + email + "</td><td>" + age + "</td><td>Eligible</td></tr></table>");
		}
		else {
			out.println("<table border = 1><tr><th>Name</th><th>Email</th><th>Age</th><th>Eligiblity</th></tr>");
			out.println("<tr><td>" + name + "</td><td>" + email + "</td><td>" + age + "</td><td>Not Eligible</td></tr></table>");
		}
	}

}
