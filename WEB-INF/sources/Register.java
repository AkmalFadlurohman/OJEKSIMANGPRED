package com.ojeksimangpred.IDServices;

import java.io.*;
import java.lang.ClassNotFoundException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.Gson;
import com.ojeksimangpred.bean.User;

public class Register extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		
		User user = new User();
		AccessManager AM = new AccessManager();
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("user_password");
		String cpassword = request.getParameter("confirm_password");
		String phone = request.getParameter("phone");
		String status;
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("is_driver") != null) {
			status = "driver";
		} else {
			status = "customer";
		}
		if (password.equals(cpassword) ) {
			insertToDB(fullname,username,email,password,phone,status);
			user = UserManager.fetchUser(username);
			String token = AM.generateToken(username);
			user.setToken(token);
			Gson gson = new Gson();
			String json = gson.toJson(user);
			response.sendRedirect("../profile/profile.jsp?user="+json);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
		}
	}
	
	public void insertToDB(String fullname, String username, String email, String password,String phone, String status) {
		Connection connect = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			if (connect != null) {
				String query = "INSERT INTO user (name, email, phone, username, password,status)" + " VALUES (?, ?, ?, ?, ?, ? )";
				
				PreparedStatement preparedStmt = connect.prepareStatement(query);
		    		preparedStmt.setString(1, fullname);
		    		preparedStmt.setString(2, email);
		    		preparedStmt.setString(3, phone);
		    		preparedStmt.setString(4, username);
		    		preparedStmt.setString(5, password);
		    		preparedStmt.setString(6, status);
		    
		    		preparedStmt.executeUpdate();
		    
				if (preparedStmt != null) {
					connect.close();			
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
