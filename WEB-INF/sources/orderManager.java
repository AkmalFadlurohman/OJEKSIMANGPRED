/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ojeksimangpred.OjolServices;

import static java.lang.System.out;
import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.ojeksimangpred.bean.User;
import com.ojeksimangpred.bean.Driver;
import com.ojeksimangpred.bean.Order;
/**
 *
 * @author user
 */
public class OrderManager {
    private Order currOrder;

    public OrderManager() {
        this.currOrder = new Order();
    }

    public void setOrder(int idDriver, int idCustomer, int score, String destLoc, String pickLoc, String currComment, boolean driverVisibility, boolean customerVisibility){
        currOrder.setDriverId(idDriver);
        currOrder.setCustomerId(idCustomer);
        currOrder.setScore(score);
        currOrder.setDestLoc(destLoc);
        currOrder.setPickLoc(pickLoc);
        Calendar calendar = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
        currOrder.setDate(date);
        currOrder.setComment(currComment);
        currOrder.setDriverVisibility(driverVisibility);
        currOrder.setCustomerVisibility(customerVisibility);
    }
    
    public void insertToDatabase(){
        try
        {
          // create a mysql database connection
          String myDriver = "com.mysql.jdbc.Driver";
          String myUrl = "jdbc:mysql://localhost:3306/ojeksimangpred_ojolservices";
          Class.forName(myDriver);
            // create a sql date object so we can use it in our INSERT statement
            try (Connection conn = DriverManager.getConnection(myUrl, "root", "")) {
                // create a sql date object so we can use it in our INSERT statement
                String query = "SELECT max(order_id) as `id` FROM `order`";

                  // create the java statement
                  Statement st = conn.createStatement();
                  
                  // execute the query, and get a java resultset
                  ResultSet rs = st.executeQuery(query);
                  
                  // iterate through the java resultset
                  int id = 0;
                  while (rs.next())
                  {
                    id = rs.getInt("id");
                    // print the results
                  }
                  st.close();
                
                
                // the mysql insert statement
                query = " insert into `order` (order_id, dest_city, pick_city, score, comment, driver_id, cust_id, date, customer_visibility, driver_visibility)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt       (1, (id+1));
                preparedStmt.setString    (2, currOrder.getDestLoc());
                preparedStmt.setString    (3, currOrder.getPickLoc());
                preparedStmt.setInt       (4, currOrder.getScore());
                preparedStmt.setString    (5, currOrder.getComment());
                preparedStmt.setInt       (6, currOrder.getDriverId());
                preparedStmt.setInt       (7, currOrder.getCustomerId());
                preparedStmt.setDate      (8, currOrder.getDate());
                preparedStmt.setBoolean   (9, currOrder.getCustomerVisibility());
                preparedStmt.setBoolean   (10, currOrder.getDriverVisibility());
                preparedStmt.execute();
            } catch (Exception e){
            }
        }
        catch (Exception e)
        {
        }
    }
    
    public Order getOrder(){
        return (currOrder);
    }
    
    public void hideOrder(int id, boolean isDriver){
        try
        {
          // create a mysql database connection
          String myDriver = "com.mysql.jdbc.Driver";
          String myUrl = "jdbc:mysql://localhost:3306/ojeksimangpred_ojolservices";
          Class.forName(myDriver);
            // create a sql date object so we can use it in our INSERT statement
            try (Connection conn = DriverManager.getConnection(myUrl, "root", "")) {

                // the mysql insert statement
                String query;
                if (isDriver) {
                    query = " update `order` set driver_visibility = false where id = " + id;
                } else {  
                    query = " update `order` set customer_visibility = false where id = " + id;
                }
                
                Statement st = conn.createStatement();
                st.executeQuery(query);
                
            } catch (Exception e){
            }
        }
        catch (Exception e)
        {
        }
    }
}