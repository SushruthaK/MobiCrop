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

@WebServlet("/shopPestServlet")
public class shopPestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");

		String pname = request.getParameter("pname");

		String qty = request.getParameter("qty");

		String cost = request.getParameter("cost");

		String cname = request.getParameter("cname");

		try {
			Statement st;
			st = DbConnection.getConnection().createStatement();
			ResultSet rs = st
					.executeQuery("select * from shop_pest where shop_id='"
							+ sid + "' and pest_name='" + pname + "'");
			//System.out.println("rs:" + rs.next());

			if (rs.next()) {
				System.out.println("update");
				Statement psmt = DbConnection.getConnection().createStatement();
				int a = psmt.executeUpdate("update shop_pest set quantity="
						+ qty + ",cost=" + cost + ",company_name='" + cname
						+ "' where shop_id='" + sid + "' and pest_name='"
						+ pname + "'");
				System.out.println("a :"+a);
			}

			else {
				PreparedStatement psmt1 = DbConnection
						.getConnection()
						.prepareStatement(
								"insert into shop_pest(SHOP_ID,PEST_NAME,QUANTITY,COST,COMPANY_NAME) VALUES(?,?,?,?,?)");
				psmt1.setString(1, sid);
				psmt1.setString(2, pname);
				psmt1.setString(3, qty);
				psmt1.setString(4, cost);
				psmt1.setString(5, cname);
				int a = psmt1.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
