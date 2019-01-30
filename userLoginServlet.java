package com.app.mobicrop;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.mobicrop.DbConnection;


@WebServlet("/userLoginServlet")
public class userLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String username,password;
		String uname=request.getParameter("uname");
		
		String upswd=request.getParameter("upswd");
		try{
			Statement st;
			st = DbConnection.getConnection().createStatement();
			ResultSet rs = st
					.executeQuery("select * from users ");
			
			while(rs.next()) {
				 username=rs.getString(2);
				 password=rs.getString(3);
				 if(username.equals(uname) && password.equals(upswd)){
					 response.sendRedirect("userfeedback.jsp");
				 }
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
