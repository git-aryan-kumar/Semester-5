package mypackage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String op = request.getParameter("op");
		Double first = Double.parseDouble(request.getParameter("first"));
		Double second = Double.parseDouble(request.getParameter("second"));
		Double result;
		if (op.equals("Addition")) {
			result = first + second;
			out.println("<h1>Result of the " + op + " operation is: </h1>");
			out.println("Result of the operation is " + result);
		}
		else if (op.equals("Subtraction")) {
			result = second - first;
			out.println("<h1>Result of the " + op + " operation is: </h1>");
			out.println("Result of the operation is " + result);
		}
		else if (op.equals("Multiplication")) {
			result = first * second;
			out.println("<h1>Result of the " + op + " operation is: </h1>");
			out.println("Result of the operation is " + result);
		}
		else if (op.equals("Division")) {
			if (second == 0) {
				out.println("<h1>Result of the " + op + " operation is: </h1>");
				out.println("Zero Division Error");
			}
			else {
				result = first/second;
				out.println("<h1>Result of the " + op + " operation is: </h1>");
				out.println("Result of the operation is " + result);
			}
		}
		else if (op.equals("Exponent")) {
			result = Math.exp(first);
			out.println("<h1>Result of the " + op + " operation is: </h1>");
			out.println("Result of the operation is " + result);
		}
		
	}
}
