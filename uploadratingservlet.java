package com.app.mobicrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.mobicrop.DbConnection;






@WebServlet("/uploadratingservlet")
public class uploadratingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String area=request.getParameter("area1");
			System.out.println(area);
			String sname = request.getParameter("slist");
			System.out.println(sname);
			String pname = request.getParameter("plist");
			String rating = request.getParameter("rating");
			System.out.println(rating);
			String feedback=request.getParameter("feedback");
			
			String sql = "INSERT INTO userrating (sname,area,rating,feedback,pname) values (?,?,?,?,?)";
			
			PreparedStatement ps = DbConnection.getConnection()
					.prepareStatement(sql);
			ps.setString(1, sname);
			ps.setString(2, area);
			ps.setString(3, rating);
			ps.setString(4, feedback);
			ps.setString(5, pname);
				int a = ps.executeUpdate();
			if (a > 0) {
				response.sendRedirect("userfeedback.jsp");
			} else {
				response.sendRedirect("failure.jsp");
			}
			//String sql1 = "select shop_id from shop where shop_name='" +sname+"'";
			Statement st = DbConnection.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select shop_id from shop where shop_name='" +sname+"'");
			rs.next();
			String sid=rs.getString(1);
			System.out.println(sid);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
