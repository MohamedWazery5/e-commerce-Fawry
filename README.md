# e-commerce-Fawry
Product Management  Products can expire (e.g., cheese) or be non-perishable (e.g., TV)  Some require shipping (with weights), others don't (e.g., digital cards)  Shopping Cart  Add products with quantity validation (stock checks)  Prevent expired/out-of-stock items  Checkout System  Calculate subtotal, shipping fees.



## Example Usage

```java
public static void main(String[] args) {
    Product cheese = new ExpireProduct("Cheese", 100, 10, 0.4, true);
    Product biscuits = new ExpireProduct("Biscuits", 150, 5, 0.7, true);
    
    Cart cart = new Cart();
    cart.add(cheese, 2);
    cart.add(biscuits, 1);
    
    CheckoutService checkout = new CheckoutService(new ShippingService());
    checkout.checkout(cart, new User("Customer", 1000));
}


Sample Output

** Shipment notice **
2x Cheese          800g (400g × 2)
1x Biscuits        700g
Total package weight 1.5kg

** Checkout receipt **
2x Cheese          200
1x Biscuits        150
----------------------
Subtotal              350
Shipping              15
Amount                365
Remaining balance:  635


Business Rules
Shipping:

Only for products marked shippable=true

Weight must be > 0 for shippable products

$10 per kg shipping rate

Validation:

-Quantity cannot exceed available stock
-No expired products
-Sufficient customer balance
