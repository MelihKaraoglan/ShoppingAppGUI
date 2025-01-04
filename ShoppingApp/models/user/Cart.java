package models.user;
import java.util.ArrayList;
import java.util.List;

import models.shared.Product;


public class Cart {
    private List<Product> products;

    public Cart(){
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeProduct(Product product) {
        products.remove(product);
        System.out.println(product.getName() + " removed from cart.");
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

}