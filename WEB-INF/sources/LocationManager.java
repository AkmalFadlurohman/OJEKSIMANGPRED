package com.ojeksimangpred.OjolServices;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@WebService(endpointInterface = "com.ojeksimangpred.OjolServices.LocationManagerInterface")
public class LocationManager implements LocationManagerInterface {
	
	@Override
	public ArrayList retrieveLocation(int driverID) {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList Locations = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_IDServices","root","");
		
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select pref_loc from driver_prefloc where driver_id='"+driverID+"'");
			while (resultSet.next()) {
				Locations.add(resultSet.getString("pref_loc"));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Locations;
	}
	@Override
	public void addLocation(int driverID,String location) {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_OjolServices","root","");
			if (connect != null) {
				String query = "INSERT INTO driver_prefloc (driver_id, pref_loc)" + " VALUES (?, ?)";
				
				PreparedStatement preparedStmt = connect.prepareStatement(query);
		    		preparedStmt.setInt(1, driverID);
		    		preparedStmt.setString(2, location);
		    
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
	@Override
	public void editLocation(int driverID,String oldLocation,String newLocation) {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_OjolServices","root","");
			if (connect != null) {
				String query="UPDATE driver_prefloc set pref_loc= ? WHERE driver_id= ? AND pref_loc= ?";
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, newLocation);
				preparedStatement.setInt(2, driverID);
				preparedStatement.setString(3, oldLocation);
				
				int row = preparedStatement.executeUpdate();
				if (row > 0) {
					connect.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delLocation(int driverID,String deleteLocation) {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ojeksimangpred_OjolServices","root","");
			if (connect != null) {
				String query="DELETE FROM driver_prefloc WHERE driver_id= ? AND pref_loc= ?";
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, driverID);
				preparedStatement.setString(2, deleteLocation);
				
				int row = preparedStatement.executeUpdate();
				if (row > 0) {
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