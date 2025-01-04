package models.shared;

import models.user.Cart;
import models.user.PaymentInfo;


// Subclass: Customer
public class Customer extends User {

    private Cart cart;
    private PaymentInfo paymentInfo;  // Yeni eklenen PaymentInfo

    public Customer(String name, String email, String password, Role role) {
        super(name, email, password, role);
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    // Yeni ödeme bilgileri ekleyen metod
    public void setPaymentInfo(String cardNumber, String expirationDate, String cvv) {
        this.paymentInfo = new PaymentInfo(cardNumber, expirationDate, cvv);
    }

    // Ödeme bilgilerini almak için bir getter metodu
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
}