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
public class Driver {
   int id;
   String[] location;
   int totalVotes;
   int totalScore;

   int getId() {
       return (id);
   }

   public String toString() {
    return ("driver [id=" + id + ", totalVotes=" + totalVotes + ", totalScore=" + totalScore + "]");
  }

   public String[] getLocation() {
       return (location);
   }

   public float getRating() {
       return (totalScore/totalVotes);
   }
   
   public float getVotes() {
       return (totalVotes);
   }
   
   public int getTotalScore(){
       return (totalVotes);
   }
   
   public void setId(int x) {
       id = x;
   }

   public void setLocation(String locationx) {
       location[location.length] = locationx;
   }

   public void setVotes(int x) {
       totalVotes = x;
   }
   
   public void setTotalScore(int y){
       totalScore = y;
   }
}
