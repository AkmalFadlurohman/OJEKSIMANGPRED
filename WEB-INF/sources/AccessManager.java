package com.ojeksimangpred.IDServices;

import java.io.*;
import java.lang.ClassNotFoundException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.ojeksimangpred.OjolServices.LocationManagerInterface;
import com.nimbusds.jwt.*;
import com.google.gson.Gson;
import com.ojeksimangpred.bean.*;

public class AccessManager extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		String action = (String) request.getAttribute("action");
		PrintWriter out = response.getWriter();
		if (action != null) {
			if (action.equals("checkAccess")) {
				Cookie cookies[] = request.getCookies();
				boolean tokenExist = false;
				boolean unameExist = false;
				if (cookies != null) {
					int i;
					for (i=0;i<cookies.length;i++) {
						if (cookies[i].getName().equals("accessToken")) {
							tokenExist = true;
							break;
						}
					}
					int j;
					for (j=0;j<cookies.length;j++) {
						if (cookies[j].getName().equals("username")) {
							unameExist = true;
							break;
						}
					}
					if (tokenExist && unameExist) {
						Cookie tokenCookie = cookies[i];
						Cookie unameCookie = cookies[j];
						String token = tokenCookie.getValue();
						String username = unameCookie.getValue();
						User user = UserManager.fetchUser(username);
						user.setToken(token);
						Gson gson = new Gson();
						String json = gson.toJson(user);
						response.sendRedirect("../profile/profile.jsp?user="+json);
					}
				} else {
					response.sendRedirect("../login/login.html");
				}	
			} 
		} else {
			out.println("Error");
		}
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		User user = UserManager.fetchUser(username);
		Gson gson = new Gson();
		String uJson = gson.toJson(user);
		Driver driver = UserManager.fetchDriver(user.getId());
		String dJson = gson.toJson(driver);
		PrintWriter out = response.getWriter();
		if ("addLocation".equals(action)) {
			URL url = new URL("	http://www.ojeksimangpred.com/OjolServices/LocationManager?wsdl");
			
			QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "LocationManagerService");
			
			Service service = Service.create(url, qname);
			
			LocationManagerInterface LM = service.getPort(LocationManagerInterface.class);
			
			int driverID = Integer.parseInt(request.getParameter("driverId"));
			String newLoc = request.getParameter("new_location");
			LM.addLocation(driverID,newLoc);
			response.sendRedirect("../profile/edit_location.jsp?user="+uJson+"&driver="+dJson);
			
		} else if ("updateLocation".equals(action)) {
			URL url = new URL("	http://www.ojeksimangpred.com/OjolServices/LocationManager?wsdl");
			
			QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "LocationManagerService");
			
			Service service = Service.create(url, qname);
			
			LocationManagerInterface LM = service.getPort(LocationManagerInterface.class);
			
			int driverID = Integer.parseInt(request.getParameter("driverId"));
			String currentPrefloc = request.getParameter("current_prefloc");
			String newPrefloc = request.getParameter("new_prefloc");
			LM.editLocation(driverID,currentPrefloc,newPrefloc);
			response.sendRedirect("../profile/edit_location.jsp?user="+uJson+"&driver="+dJson);
		}
		/*if (validateToken(user.getToken())) {
		} else {
			response.sendRedirect("../IDServices/Logout?user="+uJson);
		}*/

	}
	
	
	public void generateToken(String username) {
		SecureRandom random = new SecureRandom();
		byte[] sharedSecret = new byte[64];
		random.nextBytes(sharedSecret);
		
		String token = null;
		try {
			JWSSigner signer = new MACSigner(sharedSecret);
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
			.subject(username)
			.issuer("ojeksimangpred.com")
			.expirationTime(new Date(new Date().getTime() + 3600 * 1000))
			.build();
			
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
			signedJWT.sign(signer);
			token = signedJWT.serialize();
		} catch(KeyLengthException e ) {
			e.printStackTrace();
		} catch(JOSEException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			if (connect != null) {
				statement = connect.createStatement();
		    		statement.executeUpdate("UPDATE user SET token = '"+token+"',secret = '"+ sharedSecret.toString() +"' WHERE username = '"+username+"'");
		    
				if (statement != null) {
					connect.close();			
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	boolean validateToken(String token) {
		byte[] sharedSecret = new byte[64];
		Connection connect = null;
		ResultSet resultSet = null;
		Statement statement = null;
		boolean valid = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			if (connect != null) {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("SELECT  token,secret  FROM user WHERE token = '"+token+"'");
		    
				if (resultSet != null) {
					sharedSecret = resultSet.getString("secret").getBytes();
					
					try {
						SignedJWT signedJWT = SignedJWT.parse(token);

						JWSVerifier verifier = new MACVerifier(sharedSecret);

						if (signedJWT.verify(verifier)) {
							Date expDate = signedJWT.getJWTClaimsSet().getExpirationTime();
							Date curDate = new Date(new Date().getTime());
							if (curDate.before(expDate)) {
								valid = true;
							}							
						}
					} catch(ParseException e ) {
						e.printStackTrace();
					} catch(JOSEException e) {
						e.printStackTrace();
					}
					connect.close();			
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return valid;
	}
}
