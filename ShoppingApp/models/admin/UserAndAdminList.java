package models.admin;

import utils.DataStorage;
import java.util.List;
import java.util.stream.Collectors;


import models.shared.User;
import models.shared.Admin;
import models.shared.Customer;
import models.shared.User.Role;

public class UserAndAdminList {
    private UserManager userManager;

    public UserAndAdminList() {
        userManager = new UserManager();
        initializeUsers();
    }

    // Kullanıcıları başlatma (eğer JSON dosyası boşsa)
    private void initializeUsers() {
        if (userManager.getAllUsers().isEmpty()) {
            userManager.addUser("melihkaraoglann@gmail.com", "Melih Karaoğlan", "melihbaba5858", User.Role.ADMIN);
            userManager.addUser("ardasakarya@gmail.com", "Arda Sakarya", "ardababapiro", User.Role.ADMIN);
            userManager.addUser("john@example.com", "John Doe", "jjbabajava", User.Role.USER);
            userManager.saveUsers();
        }
    }

    // Kullanıcı doğrulama
    public User validateUser(String email, String password) {
        User user = userManager.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Kullanıcı bulunamadı
    }

    // Kullanıcı giriş kontrolü
    public boolean login(String email, String password, User.Role role) {
        User user = userManager.getUserByEmail(email);
        return user != null && user.getPassword().equals(password) && user.getRole() == role;
    }

    // Email ile Admin bulma
    public Admin getAdminByEmail(String email) {
        User user = userManager.getUserByEmail(email);
        if (user instanceof Admin) {
            return (Admin) user;
        }
        return null;
    }

    // Email ile Customer bulma
    public Customer getCustomerByEmail(String email) {
        User user = userManager.getUserByEmail(email);
        if (user instanceof Customer) {
            return (Customer) user;
        }
        return null;
    }

    public void addUser(User user) {
        userManager.addUser(user.getEmail(), user.getName(), user.getPassword(), user.getRole());
        userManager.saveUsers();
    }

    public void removeUser(String email) {
        userManager.removeUser(email);
        userManager.saveUsers();
    }

    public void saveUsers() {
        userManager.saveUsers(); // userManager bir UserManager nesnesi olmalı
    }
    

    // Tüm kullanıcıları liste olarak döndür
    public List<User> getUsers() {
        return userManager.getAllUsers().values().stream().collect(Collectors.toList());
    }
}
