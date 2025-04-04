/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Wazery
 */
public class User {
   private String name; 
   private double balance; 
   public User(String name,double balance){
       this.name=name;
       this.balance=balance;
   }
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
