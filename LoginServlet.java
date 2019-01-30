package com.app.mobicrop;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("upswd");
		if(username.equals("admin") && password.equals("mobicrop"))
		{
			response.sendRedirect("success.jsp");
		}
		else
		{
			response.sendRedirect("failure.jsp");
		}
	}
}
