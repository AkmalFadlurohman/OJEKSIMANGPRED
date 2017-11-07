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

public class UserManager extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		String json;
		String newName;
		String newPhone;
		String newStatus;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		PrintWriter out = response.getWriter();
		out.println(isMultipart);
		if (isMultipart) {
			try {
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						// Process regular form field (input type="text|radio|checkbox|etc", select, etc).
						String fieldname = item.getFieldName();
						String fieldvalue = item.getString();
						if (fieldname.equals("newName")) {
							newName = fieldvalue;
							out.println(newName);
						} else if (fieldname.equals("newPhone")) {
							newPhone = fieldvalue;
							out.println(newPhone);
						} else if(fieldname.equals("newStatus")) {
							if (fieldvalue != null) {
								newStatus = "customer";
							} else {
								newStatus = "driver";
							}
							out.println(newStatus);
						} else if(fieldname.equals("user")) {
							json = fieldvalue;
							out.println(json);
						}
					} else {
						String fieldname = item.getFieldName();
						//String filename = FilenameUtils.getName(item.getName());
						InputStream filecontent = item.getInputStream();
					}
				}
			} catch(FileUploadException ex) {
				ex.printStackTrace();
			}
		} 
		
		
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement;
		
		/*if (request.getParameter("newStatus") != null) {
			newStatus = "driver";
		} else {
			newStatus = "customer";
		}*/
		
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
			
			InputStream  inputStream = null; 
			Part filePart = request.getPart("pictFile");
			if (filePart != null && filePart.getSize() > 0) {
				inputStream = filePart.getInputStream();
				String query="UPDATE user set name= ? ,phone= ? ,status= ?,pict= ? WHERE id= ? ";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, newName);
				preparedStatement.setString(2, newPhone);
				preparedStatement.setString(3, newStatus);
				preparedStatement.setBlob(4, inputStream);
				preparedStatement.setInt(5, user.getId());
			} else {
				//String query="UPDATE user set name='"+newName+"',phone='"+newPhone+"',status='"+newStatus+"' WHERE id='"+user.getId()+"'";
				String query="UPDATE user set name= ? ,phone= ? ,status= ? WHERE id= ? ";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, newName);
				preparedStatement.setString(2, newPhone);
				preparedStatement.setString(3, newStatus);
				preparedStatement.setInt(4,user.getId());
			}
			int row = preparedStatement.executeUpdate();
            if (row > 0) {
                connect.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
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
}