package com.app.mobicrop;
import com.app.mobicrop.DbConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/costServlet")
public class costServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String qty,cost,com;
       int rating,avg=0;
       	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       		String sid=request.getParameter("shop_id");
       		String pname=request.getParameter("pest_name");
       		System.out.println("sid:"+sid);
       		System.out.println("pname:"+pname);
       		Statement st;
			try {
				st = DbConnection.getConnection().createStatement();
				ResultSet rs = st.executeQuery("select * from shop_pest where shop_id='"+ sid + "' and pest_name='" + pname + "'");
				
				/*String Query="select * from shop_pest where shop_id='"+ sid + "' and pest_name='" + pname + "'";
				System.out.println(Query);*/
				if(rs.next())
				{
				qty=rs.getString(3);
				cost=rs.getString(4);
				com=rs.getString(5);
				/*rating=rs.getString(6);*/
				Statement st2 = DbConnection.getConnection().createStatement();
				ResultSet rs2 = st2.executeQuery("select shop_name from shop where shop_id='" + sid +"'");
				rs2.next();
				String sname=rs2.getString(1);
				Statement st1 = DbConnection.getConnection().createStatement();
				ResultSet rs1 = st1.executeQuery("select rating from userrating where sname='"+ sname +"' and pname='" + pname +"' ");
				
				while(rs1.next())
				{
					System.out.println(rs1.getString(1));
					avg=(avg+Integer.parseInt(rs1.getString(1)))/2;
				}
				
				rating=avg;
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("Quantity",qty);
			request.setAttribute("Cost", cost);
			request.setAttribute("CompanyName", com);
			request.setAttribute("Rating", rating);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("/cost.jsp");
			dispatcher.forward(request, response);
			
			
	
	}

}
