/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Wazery
 */
public class ExpireProduct extends Product {
    private LocalDate expiryDate;

    public ExpireProduct(String name, double price, int quantity, LocalDate expiryDate,double weight,boolean shippable) {
        super(name, price, quantity,weight,shippable);
        this.expiryDate = expiryDate;
    }


    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}