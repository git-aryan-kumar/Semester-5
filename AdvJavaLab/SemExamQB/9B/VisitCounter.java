package mypackage;

import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/VisitCounter")
public class VisitCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Integer visitCount = (Integer) session.getAttribute("visitCount");
		
		if (visitCount == null) {
			visitCount = 1;
			session.setAttribute("visitCount", visitCount);
			out.println("<h2>First Visit. Visit count is: " + visitCount + "</h2>");
		}
		else {
			visitCount++;
			session.setAttribute("visitCount", visitCount);
			out.println("<h2>Number of visits is: " + visitCount + "</h2>");
		}
		
	}

}
