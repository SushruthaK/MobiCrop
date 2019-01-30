package com.app.mobicrop;
import com.app.mobicrop.DbConnection;
import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PestServlet")
public class PestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("pname");
		
	
		try{
			PreparedStatement psmt=DbConnection.getConnection().prepareStatement("insert into pest(PEST_NAME) VALUES(?)");
			psmt.setString(1, name);
			int a=psmt.executeUpdate();
			if (a>0) {
				
				response.sendRedirect("success.jsp");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
		
	

}
