/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ojeksimangpred.bean;

/**
 *
 * @author user
 */
public class Order {
    int orderId;
    int driverId;
    int customerId;
    int score;
    String destLoc;
    String pickLoc;   
    java.sql.Date date;
    String comment;
    boolean customerVisibility;
    boolean driverVisibility;
    
    @Override
    public String toString() {
        return ("order [driverId=" + driverId + ", customerId=" + customerId + ", score=" + score + ", destLoc=" + destLoc + ", pickLoc=" + pickLoc +", date=" + date +", comment=" + comment +", customerVisibility=" + customerVisibility +", driverVisibility=" + driverVisibility + "]");
    }
    public int getOrderId(){
        return(orderId);
    }
    public int getDriverId(){
        return(driverId);
    }
    public int getCustomerId(){
        return(customerId);
    }
    public int getScore(){
        return(score);
    }
    public String getDestLoc(){
        return(destLoc);
    }
    public String getPickLoc(){
        return(pickLoc);
    }   
    public java.sql.Date getDate(){
        return(date);
    }
    public String getComment(){
        return(comment);
    }
    public void setOrderId(int idZ){
        orderId = idZ;
    }
    public void setDriverId(int idX){
        driverId = idX;
    }
    public void setCustomerId(int idY){
        customerId = idY;
    }
    public void setScore(int x){
        score = x;
    }
    public void setDestLoc(String locX){
        destLoc = locX;
    }
    public void setPickLoc(String locY){
        pickLoc = locY;
    }   
    public void setDate(java.sql.Date currDate){
        date = currDate;
    }
    public void setComment(String currComment){
        comment = currComment;
    }
    public void setCustomerVisibility(boolean x){
        customerVisibility = x;
    }
    
    public void setDriverVisibility(boolean y){
        driverVisibility = y;
    }
    
    public boolean getCustomerVisibility(){
        return(customerVisibility);
    }
    public boolean getDriverVisibility(){
        return(driverVisibility);
    }
}
