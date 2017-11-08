package com.ojeksimangpred.IDServices;

import java.io.*;
import java.util.*;  
import java.lang.ClassNotFoundException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;
import com.google.gson.Gson;
import com.ojeksimangpred.bean.User;
import com.ojeksimangpred.bean.Driver;

public class UserManager extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		String username = null;
		String newName = null;
		String newPhone = null;
		String newStatus = "customer";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		InputStream inputStream = null;
		boolean isFileUploaded = false;
		PrintWriter out = response.getWriter();
		
		if (isMultipart) {
			ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
			try {
				List<FileItem> fileItems = servletFileUpload.parseRequest(request);
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) {
						if (fileItem.getFieldName().equalsIgnoreCase("newName")) {
							newName = fileItem.getString();
						} else if (fileItem.getFieldName().equalsIgnoreCase("newPhone")) {
							newPhone = fileItem.getString();
						} else if(fileItem.getFieldName().equalsIgnoreCase("newStatus")) {
							if (fileItem.getString().equalsIgnoreCase("on")) {
								newStatus = "driver";
							}
						} else if(fileItem.getFieldName().equalsIgnoreCase("userName")) {
							username = fileItem.getString();
						}
					} else {
						inputStream = fileItem.getInputStream();
						String fileName = new File(fileItem.getName()).getName();
						if (!"".equals(fileName)) {
							isFileUploaded = true;
						}
					}
				}
			} catch(FileUploadException ex) {
				ex.printStackTrace();
			}
		} 
		
		
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			
			if (isFileUploaded) {
				String query="UPDATE user set name= ? ,phone= ? ,status= ?,pict= ? WHERE username= ? ";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, newName);
				preparedStatement.setString(2, newPhone);
				preparedStatement.setString(3, newStatus);
				preparedStatement.setBlob(4, inputStream);
				preparedStatement.setString(5, username);
				int row = preparedStatement.executeUpdate();
				if (row > 0) {
					//out.println("Success");
					connect.close();
				}
			} else {
				String query="UPDATE user set name= ? ,phone= ? ,status= ? WHERE username= ? ";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, newName);
				preparedStatement.setString(2, newPhone);
				preparedStatement.setString(3, newStatus);
				preparedStatement.setString(4,username);
				int row = preparedStatement.executeUpdate();
				if (row > 0) {
					connect.close();
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		User user = new User();
		Driver driver = new Driver();
		AccessManager AM = new AccessManager();
		user = UserManager.fetchUser(username);
		Gson gson = new Gson();
		String uJson = gson.toJson(user);
		if (user.getStatus().equals("driver")) {
			driver = UserManager.fetchDriver(user.getId());
			String dJson = gson.toJson(driver);
			response.sendRedirect("../profile/profile.jsp?user="+uJson+"&driver="+dJson);
		} else {
			response.sendRedirect("../profile/profile.jsp?user="+uJson);
		}
	}
	public static User fetchUser(String username) {
		User user = new User();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from user where username='"+username+"'");
			if (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setUsername(resultSet.getString("username"));
				user.setToken(resultSet.getString("token"));
				user.setStatus(resultSet.getString("status"));
			}
			connect.close();
		} catch (SQLException e) {
			user.setFullname(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
	public static Driver fetchDriver(int driverID) {
		Driver driver = new Driver();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from driver where driver_id='"+driverID+"'");
			if (resultSet.next()) {
				driver.setId(resultSet.getInt("driver_id"));
				driver.setVotes(resultSet.getInt("total_score"));
				driver.setTotalScore(resultSet.getInt("votes"));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return driver;
	}
}