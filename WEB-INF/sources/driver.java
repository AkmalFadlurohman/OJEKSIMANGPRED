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
public class driver {
   int id;
   String[] location;
   int jumlahOrder;
   int totalScore;

   int getId() {
       return (id);
   }

   public String toString() {
    return ("driver [id=" + id + ", location=" + location + ", jumlahOrder=" + jumlahOrder + ", totalScore=" + totalScore + "]");
  }

   String[] getLocation() {
       return (location);
   }

   float getRating() {
       return (totalScore/jumlahOrder);
   }
   
   float getVotes() {
       return (jumlahOrder);
   }
   
   int getTotalScore(){
       return (jumlahOrder);
   }
   
    void setId(int x) {
       id = x;
   }

   void setLocation(String locationx) {
       location[location.length] = locationx;
   }

   void setVotes(int x) {
       jumlahOrder = x;
   }
   
   void setTotalScore(int y){
       totalScore = y;
   }
}
