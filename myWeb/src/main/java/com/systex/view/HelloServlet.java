package com.systex.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String croot = request.getContextPath();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>HelloServlet</title>");
		out.println("<link href=\"" + croot + "/style/myStyle.css\" rel=\"stylesheet\"></link>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>HelloServlet</h2>");
		out.println("<p>current server time is: " + SimpleDateFormat.getInstance().format(new Date()) + "</p>");
		out.println("<table border='1' style='margin:auto'>");
		out.println("<tbody>");
		for (int i = 1; i <= 9; i++) {
			out.println("<tr>");
			for (int j = 1; j <= 9; j++) {
				out.println("<td>" + i + "*" + j + "=</td><td>" + i * j + "</td>");
			}
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<br/><img src='" + croot + "/image/pic1.webp' width='500px'/>");
		out.println("<br><a href='" + croot + "/'>Go Home</br>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
