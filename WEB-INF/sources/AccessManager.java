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
import java.util.Date;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.ojeksimangpred.bean.User;

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
					//out.println(cookie.getName());
					//out.println(cookie.getValue());
				} else {
					response.sendRedirect("../login/login.html");
				}	
			} 
		} else {
			out.println("Error");
		}
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
			e.printStackTrace();;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*boolean checkTokenExpiry(String username, String token) {
		SecureRandom random = new SecureRandom();
		byte[] sharedSecret = new byte[64];
		random.nextBytes(sharedSecret);
		
		try {
			JWSSigner signer = new MACSigner(sharedSecret);
			
			
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
			signedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(sharedSecret);
			assertTrue(signedJWT.verify(verifier));
		} catch(KeyLengthException e ) {
			e.printStackTrace();
		} catch(JOSEException e) {
			e.printStackTrace();
		}
	}*/
}
