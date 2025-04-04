/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Product;
import Model.ShippableInterface;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Wazery
 */
public class ShippingService implements ShippableInterface {
    private Product currentProduct;
    private static final double SHIPPING_RATE_PER_KG = 10.0;

    @Override
    public String getName() {
        if (currentProduct == null) {
            System.out.println("No product being processed");
            System.exit(0);
        }
        return currentProduct.getName();
    }

    @Override
    public double getWeight() {
        if (currentProduct == null) {
            System.out.println("No product being processed");
            System.exit(0);
        }
        return currentProduct.getWeight();
    }
    
    public double processShipment(List<Product> products) {
        List<Product> shippableProducts = products.stream()
            .filter(Product::isShippable)
            .collect(Collectors.toList());

        if (shippableProducts.isEmpty()) {
            System.out.println("There is no Shipment notice");
            return 0.0;
        }
        Map<String, Map<Double, Long>> groupedItems = shippableProducts.stream()
            .collect(Collectors.groupingBy(
                Product::getName,
                Collectors.groupingBy(
                    Product::getWeight,
                    Collectors.counting()
                )
            ));

        double totalWeightKg = 0;

        System.out.println("** Shipment notice **");
        for (Map.Entry<String, Map<Double, Long>> nameEntry : groupedItems.entrySet()) {
            for (Map.Entry<Double, Long> weightEntry : nameEntry.getValue().entrySet()) {
                double weightPerPieceKg = weightEntry.getKey();
                long quantity = weightEntry.getValue();
                double totalItemWeightG = weightPerPieceKg * quantity * 1000;
                System.out.printf("%dx %-15s %5.0fg%n", 
                    quantity, 
                    nameEntry.getKey(), 
                    totalItemWeightG);

                totalWeightKg += weightPerPieceKg * quantity;
            }
        }
        System.out.printf("%-19s %.1fkg%n", "Total package weight", totalWeightKg);
        System.out.println("");
        this.currentProduct = null;
        
        return totalWeightKg * SHIPPING_RATE_PER_KG;
    }
}
