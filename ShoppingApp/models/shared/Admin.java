package models.shared;



// Subclass: Admin
public class Admin extends User {

    public Admin(String name, String email,String password, Role role) {
        super(name, email, password,role); // Calls the constructor of the main class
    }

    public void addProduct(Product product) {
        System.out.println("Product " + product.getName() + " added to the store.");
    }

    public void removeProduct(Product product) {
        System.out.println("Product " + product.getName() + " removed from the store.");
    }
}