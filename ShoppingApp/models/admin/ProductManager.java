package models.admin;

import utils.DataStorage;
import java.util.HashMap;
import java.util.Map;

import models.shared.Product;

public class ProductManager {
    private static final String PRODUCTS_FILE = "products.json";
    private Map<String, Product> products;

    public ProductManager() {
        System.out.println("Loading products from file: " + PRODUCTS_FILE);
        products = DataStorage.loadProducts(PRODUCTS_FILE);
    }

    public void addProduct(String id, String name, double price, int stock) {
        Product product = new Product(name, price, stock);
        products.put(id, product);
        System.out.println("Product added: " + product);
    }

    public void removeProduct(String id) {
        products.remove(id);
        System.out.println("Product removed with ID: " + id);
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    public Map<String, Product> getAllProducts() {
        return products;
    }

    public void saveProducts() {
        System.out.println("Saving products to file: " + PRODUCTS_FILE);
        DataStorage.saveProducts(products, PRODUCTS_FILE);
    }
}

