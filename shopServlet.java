package com.app.mobicrop;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.mobicrop.DbConnection;

@WebServlet("/shopServlet")
public class shopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String langLat[];

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");

		String sname = request.getParameter("sname");

		String postcode = request.getParameter("loc");

		try {
			langLat = GetLangLatValues.getLatLongPositions(postcode);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

		String slat = langLat[0];

		String slong = langLat[1];
		
		

		String pesticides = request.getParameter("plist");
		String mobile = request.getParameter("mobile");

		try {
			PreparedStatement psmt = DbConnection
					.getConnection()
					.prepareStatement(
							"insert into shop(SHOP_ID,SHOP_NAME,SHOP_LAT,SHOP_LONG,PESTICIDES,MOBILE_NO,SHOP_LOCATION) VALUES(?,?,?,?,?,?,?)");
			psmt.setString(1, sid);
			psmt.setString(2, sname);
			psmt.setString(3, slat);
			psmt.setString(4, slong);
			psmt.setString(5, pesticides);
			psmt.setString(6, mobile);
			psmt.setString(7, postcode);
			int a = psmt.executeUpdate();
			if (a > 0) {

				response.sendRedirect("shop.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
