package com.app.mobicrop;

import java.io.IOException;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dup
 */
@WebServlet("/dup")
public class dup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	

		/**
		 * This class will get the lat long values.
		 * @author SANTHOSH REDDY MANDADI
		 * @version 1.0
		 * @since 20-Sep-2012
		 */
			  
		    
		    String postcode=request.getParameter("loc");
		 String latLongs[];
		try {
			latLongs = getLatLongPositions(postcode);
		
		    System.out.println("Latitude: "+latLongs[0]+" and Longitude: "+latLongs[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }
		  
		  public static String[] getLatLongPositions(String address) throws Exception
		  {
		    int responseCode = 0;
		    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
		    URL url = new URL(api);
		    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		    httpConnection.connect();
		    responseCode = httpConnection.getResponseCode();
		    if(responseCode == 200)
		    {
		      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
		      Document document = builder.parse(httpConnection.getInputStream());
		      XPathFactory xPathfactory = XPathFactory.newInstance();
		      XPath xpath = xPathfactory.newXPath();
		      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
		      String status = (String)expr.evaluate(document, XPathConstants.STRING);
		      if(status.equals("OK"))
		      {
		         expr = xpath.compile("//geometry/location/lat");
		         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         expr = xpath.compile("//geometry/location/lng");
		         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         return new String[] {latitude, longitude};
		      }
		      else
		      {
		         throw new Exception("Error from the API - response status: "+status);
		      }
		    }
		    return null;
		  }
		}


