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
public abstract class Product {


    private String name;
    private double price;
    private int quantity;
    private double weight;
    private boolean shippable;


    public Product(String name, double price, int quantity,double weight,boolean shippable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.shippable = shippable;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getWeight() {
        return weight;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }


    public boolean isShippable() {
        return shippable;
    }


    public void setShippable(boolean shippable) {
        this.shippable = shippable;
    }



}
