package com.app.mobicrop;

import java.io.IOException;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.mobicrop.DbConnection;
import com.app.model.shopInfo;

@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<shopInfo> shopinfoList = new ArrayList<shopInfo>();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Statement st;
		Double t1 = Double.parseDouble(request.getParameter("slat"));

		Double t2 = Double.parseDouble(request.getParameter("slong"));
		String pname=request.getParameter("pname");

		System.out.println("t1: " + t1);
		System.out.println("t2: " + t2);
		shopinfoList.clear();
		try {
			st = DbConnection.getConnection().createStatement();
			

			
			ResultSet rs = st.executeQuery("select * from shop where pesticides='"+pname+"'");
			
			while (rs.next()) {
				shopInfo shopInfo = new shopInfo();
				shopInfo.setSid(rs.getString(2));
				shopInfo.setSname(rs.getString(3));
				shopInfo.setSlat(Double.parseDouble(rs.getString(4)));
				shopInfo.setSlong(Double.parseDouble(rs.getString(5)));

				shopInfo.setPlist(rs.getString(6));
				shopInfo.setMobile(rs.getString(7));

				double distance = Geolocation.distance(t1, t2,
						shopInfo.getSlat(), shopInfo.getSlong(), 'K');

				NumberFormat df = DecimalFormat.getInstance();
				df.setMaximumFractionDigits(3);
				df.setRoundingMode(RoundingMode.CEILING);
				System.out.println(df.format(distance) + " Miles\n"); // displays
																		// 213.471
																		// Miles
				
				if (distance < 4 ) {

					System.out.println(shopInfo);
					shopinfoList.add(shopInfo);
					System.out.println(shopinfoList);
					

				}
				

			}
			
			request.setAttribute("shopinfoList", shopinfoList);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("shopinfo.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * String sid=request.getParameter("id");
		 * 
		 * String sname=request.getParameter("name");
		 * 
		 * String slat=request.getParameter("lat");
		 * 
		 * String slong=request.getParameter("long");
		 * 
		 * String pesticides=request.getParameter("pesticides");
		 * 
		 * try{ PreparedStatement
		 * psmt=DbConnection.getConnection().prepareStatement(
		 * "insert into shop(SHOP_ID,SHOP_NAME,SHOP_LAT,SHOP_LONG,PESTICIDES) VALUES(?,?,?,?,?)"
		 * ); psmt.setString(1, sid); psmt.setString(2, sname);
		 * psmt.setString(3, slat); psmt.setString(4, slong); psmt.setString(5,
		 * pesticides); int a=psmt.executeUpdate(); if (a>0) {
		 * 
		 * response.sendRedirect("success.jsp");
		 * 
		 * }
		 * 
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
	}

}
