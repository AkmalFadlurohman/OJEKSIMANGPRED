package com.ojeksimangpred.IDServices;

import java.io.*;
import java.lang.ClassNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;

import java.security.SecureRandom;
import java.util.Date;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.ojeksimangpred.bean.*;



public class Login extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		
		User user = new User();
		Driver driver = new Driver();
		AccessManager AM = new AccessManager();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		
		/*SecureRandom random = new SecureRandom();
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
			e.printStackTrace();;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		out.println(token);
		out.println("UPDATE user SET token = '"+token+"',secret = '"+ sharedSecret.toString() +"' WHERE username = '"+username+"'");*/
		if (this.validate(username,password)) {
			AM.generateToken(username);
			user = UserManager.fetchUser(username);
			Gson gson = new Gson();
			String uJson = gson.toJson(user);
			//Date curDate = new Date(new Date().getTime());
			//out.println(curDate);
			//Date expDate = new Date(new Date().getTime() + (3600 * 1000));
			//out.println(expDate);
			if ("driver".equals(user.getStatus())) {
				driver = UserManager.fetchDriver(user.getId());
				String dJson = gson.toJson(driver);
				response.sendRedirect("../profile/profile.jsp?user="+uJson+"&driver="+dJson);
			} else {
				response.sendRedirect("../profile/profile.jsp?user="+uJson);
			}
		}  else {
			//out.print("Username or Password incorrect");
			request.setAttribute("errorMessage", "Invalid Username or Password");
			request.getRequestDispatcher("../login/login.jsp").forward(request, response);
	    }
	}
	
	public boolean validate(String username, String password) {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String storedPassword;
		boolean valid = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
		
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select password from user where username='"+username+"'");
			if (resultSet.next()) {
				storedPassword = resultSet.getString("password");
				if (storedPassword.equals(password)) {
					valid = true;
				}
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return valid;
	}
	
}
