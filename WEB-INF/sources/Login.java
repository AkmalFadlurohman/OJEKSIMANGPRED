package com.ojeksimangpred.IDServices;

import java.io.*;
import java.lang.ClassNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.*;
import javax.servlet.http.*;
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
import com.ojeksimangpred.bean.User;




public class Login extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		
		User user = new User();
		AccessManager AM = new AccessManager();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		if (this.validate(username,password)) {
			String token = AM.generateToken(username);
			user = UserManager.fetchUser(username);
			user.setToken(token);
			
			//Cookie accessToken = new Cookie("accessToken",token);
			//Cookie uname = new Cookie("username",username);
			//accessToken.setMaxAge(180);
			//accessToken.setPath("/");
			//uname.setMaxAge(-1);
			//uname.setPath("/");
			//response.addCookie(accessToken);
			//response.addCookie(uname);
			Gson gson = new Gson();
			String json = gson.toJson(user);
			//out.println(json);
			response.sendRedirect("../profile/profile.jsp?user="+json);
			//Path path = Paths.get("default_profile.jpeg");
    	 		//byte[] defaultPict = Files.readAllBytes(path);
    	 		//response.setContentType("image/jpeg");
    	 		//response.setContentLength(defaultPict.length);
    	 		//response.getOutputStream().write(defaultPict);
		}  else {
			out.print("Username or Password incorrect");
			RequestDispatcher rs = request.getRequestDispatcher("../login/login.html");
			rs.include(request, response);
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
