package mypackage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String ename = request.getParameter("ename");
		Integer hours = Integer.parseInt(request.getParameter("hours"));
		Double rate = Double.parseDouble(request.getParameter("rate"));
		Double tax = 0.20;
		Double taxPercentage = tax * 100;
		
		Double totalPay = hours * rate;
		Double taxedPay = totalPay * tax;
		Double finalPay = totalPay - taxedPay;
		
		out.println("<h2>Employee Payroll");
		out.println("<table border = 1><tr><th>Employee Name</th><th>Hours Worked</th><th>Pay per Hour</th><th>Tax Rate</th><th>Total Pay</th><th>Pay after tax deduction</th></tr>");
		out.println("<td>" + ename + "</td><td>" + hours + "</td><td>" + rate + "</td><td>" + taxPercentage + "</td><td>" + totalPay + "</td><td>" + finalPay + "</td>");
		out.println("</table>");
		
	}
}
