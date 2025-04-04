/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.ExpireProduct;
import Model.Product;
import Model.User;
import Service.Cart;
import Service.CheckoutService;
import Service.ShippingService;
import java.time.LocalDate;

/**
 *
 * @author Wazery
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Product cheese = new ExpireProduct("Cheese", 100.00, 10, 
            LocalDate.now().minusDays(7), 0.2, true);
        Product biscuits = new ExpireProduct("Biscuits", 150, 5, 
            LocalDate.now().plusDays(14), 0.7, true);
        Product scratchCard = new Product("Scratch Card", 50, 100, 0, false) {};
        Product tv = new Product("Tv", 500, 10, 0, false) {};

        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);
        Cart cart = new Cart();
        User customer = new User("Mohamed Wazery", 3000.0);
        
        cart.add(cheese, 2);
        cart.add(tv, 3);
        cart.add(scratchCard, 1);
        checkoutService.checkout(customer,cart);
    }
}
