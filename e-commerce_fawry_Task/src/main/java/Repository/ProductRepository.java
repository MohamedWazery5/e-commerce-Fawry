/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Product;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Wazery
 */
public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product findByName(String name) {
        return products.get(name);
    }
}
