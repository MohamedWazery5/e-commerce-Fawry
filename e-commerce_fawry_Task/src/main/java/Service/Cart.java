/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Wazery
 */
public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            System.out.println("Not enough stock for " + product.getName());
            System.exit(0);
        }
        items.merge(product, quantity, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
