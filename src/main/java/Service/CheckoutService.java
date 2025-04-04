/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.ExpireProduct;
import Model.Product;
import Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wazery
 */
public class CheckoutService {
    private final ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout( User user,Cart cart) {
        validateCart(cart);
        List<Product> allProducts = new ArrayList<>();
        cart.getItems().forEach((product, quantity) -> {
            for (int i = 0; i < quantity; i++) {
                allProducts.add(product);
            }
        });

        double subtotal = calculateSubtotal(cart);
        double shipping = shippingService.processShipment(allProducts);
        double total = subtotal + shipping;

        validatePayment(user.getBalance(), total);
        updateInventory(cart);
        updateUserBalance(user, total);
        printReceipt(cart, subtotal, shipping, total, user);
    }

    private void validateCart(Cart cart) {
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        
        cart.getItems().forEach((product, quantity) -> {
            if (product instanceof ExpireProduct && ((ExpireProduct) product).isExpired()) {
                System.out.println(product.getName() + " is expired");
                System.exit(0);
            }
            if (product.getQuantity() < quantity) {
                System.out.println("Not enough stock for " + product.getName());
                System.exit(0);

            }
        });
    }

    private double calculateSubtotal(Cart cart) {
        return cart.getItems().entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    private void validatePayment(double balance, double total) {
        if (balance < total) {
            System.out.println("Insufficient balance");
            System.exit(0);
        }
    }
    private void updateUserBalance(User user, double total) {
        user.setBalance(user.getBalance() - total);
    }
    private void updateInventory(Cart cart) {
        cart.getItems().forEach((product, quantity) -> 
            product.setQuantity(product.getQuantity() - quantity)
        );
    }

    private void printReceipt(Cart cart, double subtotal, double shipping, double total, User user) {
        System.out.println("** Checkout receipt **");
        cart.getItems().forEach((product, quantity) -> 
        System.out.printf("%dx %-15s %5.0f%n", 
                quantity, product.getName(), product.getPrice() * quantity)
        );
        
        System.out.println("----------------------");
        System.out.printf("%-19s %5.0f$%n", "Subtotal", subtotal);
        System.out.printf("%-19s %5.0f$%n", "Shipping", shipping);
        System.out.printf("%-19s %5.0f$%n", "Amount", total);
        System.out.printf("Remaining balance: %.0f$%n", user.getBalance() - total);
    }
}