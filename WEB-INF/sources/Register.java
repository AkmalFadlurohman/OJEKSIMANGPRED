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
import com.ojeksimangpred.bean.Driver;

public class Register extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		
		User user = new User();
		Driver driver = new Driver(); 
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
			insertUserToDB(fullname,username,email,password,phone,status);
			AM.generateToken(username);
			user = UserManager.fetchUser(username);
			Gson gson = new Gson();
			String uJson = gson.toJson(user);
			if ("driver".equals(user.getStatus())) {
				insertDriverToDB(user.getId());
				driver = UserManager.fetchDriver(user.getId());
				String dJson = gson.toJson(driver);
				response.sendRedirect("../profile/profile.jsp?user="+uJson+"&driver="+dJson);
			} else {
				response.sendRedirect("../profile/profile.jsp?user="+uJson);
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
		}
	}
	
	public void insertUserToDB(String fullname, String username, String email, String password,String phone, String status) {
		Connection connect = null;
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
	public void insertDriverToDB(int userID) {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			if (connect != null) {
				String query = "INSERT INTO driver (driver_id, total_score, votes)" + " VALUES (?, ?, ? )";
				
				PreparedStatement preparedStmt = connect.prepareStatement(query);
		    		preparedStmt.setInt(1, userID);
		    		preparedStmt.setInt(2, 0);
		    		preparedStmt.setInt(3, 0);
		    
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
