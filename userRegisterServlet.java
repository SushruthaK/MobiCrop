package com.app.mobicrop;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.mobicrop.DbConnection;

@WebServlet("/userRegisterServlet")
public class userRegisterServlet extends HttpServlet {
	
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String uname=request.getParameter("uname");
		
		String pswd=request.getParameter("pswd");
		
		String mob=request.getParameter("mob");
	
		String loc=request.getParameter("loc");
		
	
		try{
			PreparedStatement psmt=DbConnection.getConnection().prepareStatement("insert into users(user_name,password,mobile,location) VALUES(?,?,?,?)");
			psmt.setString(1, uname);
			psmt.setString(2, pswd);
			psmt.setString(3, mob);
			psmt.setString(4, loc);
			
			int a=psmt.executeUpdate();
			if (a>0) {
				
				response.sendRedirect("userlogin.jsp");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
