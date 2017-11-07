/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author user
 */
public class order {
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

    int getDriverId(){
        return(driverId);
    }
    int getCustomerId(){
        return(customerId);
    }
    int getScore(){
        return(score);
    }
    String getDestLoc(){
        return(destLoc);
    }
    String getPickLoc(){
        return(pickLoc);
    }   
    java.sql.Date getDate(){
        return(date);
    }
    String getComment(){
        return(comment);
    }
    void setDriverId(int idX){
        driverId = idX;
    }
    void setCustomerId(int idY){
        customerId = idY;
    }
    void setScore(int x){
        score = x;
    }
    void setDestLoc(String locX){
        destLoc = locX;
    }
    void setPickLoc(String locY){
        pickLoc = locY;
    }   
    void setDate(java.sql.Date currDate){
        date = currDate;
    }
    void setComment(String currComment){
        comment = currComment;
    }
    void setCustomerVisibility(boolean x){
        customerVisibility = x;
    }
    
    void setDriverVisibility(boolean y){
        driverVisibility = y;
    }
    
    boolean getCustomerVisibility(){
        return(customerVisibility);
    }
    boolean getDriverVisibility(){
        return(driverVisibility);
    }
}
