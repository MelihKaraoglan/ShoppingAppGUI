package models.admin;

import utils.DataStorage;
import java.util.HashMap;
import java.util.Map;

import models.shared.User;
import models.shared.User.Role;

public class UserManager {
    private static final String USERS_FILE = "users.json";
    private Map<String, User> users;

    public UserManager() {
        System.out.println("Loading users from file: " + USERS_FILE);
        users = DataStorage.loadUsers(USERS_FILE);
    }

    public void addUser(String email, String name, String password, User.Role role) {
        User user = new User(name, email, password, role);
        users.put(email, user);
        System.out.println("User added: " + user);
    }

    public void removeUser(String email) {
        users.remove(email);
        System.out.println("User removed with email: " + email);
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public Map<String, User> getAllUsers() {
        return users;
    }

    public void saveUsers() {
        System.out.println("Saving users to file: " + USERS_FILE);
        DataStorage.saveUsers(users, USERS_FILE);
    }
}
