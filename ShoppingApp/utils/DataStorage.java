package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import models.shared.Product;
import models.shared.User;

public class DataStorage {

    // Ürünleri kaydetmek için
    public static void saveProducts(Map<String, Product> products, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(products, writer);
            System.out.println("Products saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ürünleri yüklemek için
    public static Map<String, Product> loadProducts(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Product>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("No existing product data found, initializing empty list.");
            return new HashMap<>();
        }
    }

    // Kullanıcıları kaydetmek için
    public static void saveUsers(Map<String, User> users, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
            System.out.println("Users saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcıları yüklemek için
    public static Map<String, User> loadUsers(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, User>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("No existing user data found, initializing empty list.");
            return new HashMap<>();
        }
    }
}
