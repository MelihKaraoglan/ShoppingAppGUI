package panels;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import Main.ShoppingApp;

import java.util.Map;

import models.admin.ProductManager;
import models.admin.UserManager;
import models.shared.Product;
import models.shared.User;

public class AdminPanel extends JPanel {
    private ProductManager productManager;
    private UserManager userManager;

    public AdminPanel(ProductManager productManager, UserManager userManager) {
        this.productManager = productManager;
        this.userManager = userManager;

        setLayout(new BorderLayout());
     

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel productPanel = new JPanel(new BorderLayout());
        JPanel userPanel = new JPanel(new BorderLayout());

        // Ürün yönetimi sekmesi
        createProductManagementPanel(productPanel);

        // Kullanıcı yönetimi sekmesi
        createUserManagementPanel(userPanel);

        tabbedPane.addTab("Product Management", productPanel);
        tabbedPane.addTab("User Management", userPanel);
        add(tabbedPane, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new ShoppingApp();
        });
        add(logoutButton, BorderLayout.EAST);

    }

    private void createProductManagementPanel(JPanel productPanel) {
        String[] columnNames = {"ID", "Name", "Price", "Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        // Ürünleri tabloya yükle
        loadProductsIntoTable(tableModel);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Enter product ID:");
            String name = JOptionPane.showInputDialog(this, "Enter product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter product price:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter product stock:"));
            productManager.addProduct(id, name, price, stock);
            loadProductsIntoTable(tableModel); // Tabloyu güncelle
        });
        productPanel.add(addButton, BorderLayout.NORTH);

        JButton deleteButton = new JButton("Delete Selected Product");
        deleteButton.addActionListener(e -> deleteSelectedProduct(productTable, tableModel));
        productPanel.add(deleteButton, BorderLayout.SOUTH);


    }

    private void loadProductsIntoTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Tabloyu temizle
        for (Map.Entry<String, Product> entry : productManager.getAllProducts().entrySet()) {
            String id = entry.getKey();
            Product product = entry.getValue();
            tableModel.addRow(new Object[]{id, product.getName(), product.getPrice(), product.getStock()});
        }
    }

    private void deleteSelectedProduct(JTable productTable, DefaultTableModel tableModel) {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String productId = (String) tableModel.getValueAt(selectedRow, 0);
            productManager.removeProduct(productId);
            loadProductsIntoTable(tableModel); // Tabloyu güncelle
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
        }
    }

    private void createUserManagementPanel(JPanel userPanel) {
        String[] columnNames = {"Email", "Name", "Role"};
        DefaultTableModel userModel = new DefaultTableModel(columnNames, 0);
        JTable userTable = new JTable(userModel);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        userPanel.add(userScrollPane, BorderLayout.CENTER);

        // Kullanıcıları yükle
        loadUsersIntoTable(userModel);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(e -> {
            String email = JOptionPane.showInputDialog(this, "Enter user email:");
            String name = JOptionPane.showInputDialog(this, "Enter user name:");
            String password = JOptionPane.showInputDialog(this, "Enter user password:");
            User.Role role = User.Role.valueOf(JOptionPane.showInputDialog(this, "Enter user role (ADMIN/USER):").toUpperCase());
            userManager.addUser(email, name, password, role);
            loadUsersIntoTable(userModel); // Tabloyu güncelle
        });
        userPanel.add(addUserButton, BorderLayout.NORTH);

        JButton deleteUserButton = new JButton("Delete Selected User");
        deleteUserButton.addActionListener(e -> deleteSelectedUser(userTable, userModel));
        userPanel.add(deleteUserButton, BorderLayout.SOUTH);


    }

    private void deleteSelectedUser(JTable userTable, DefaultTableModel userModel) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            String email = (String) userModel.getValueAt(selectedRow, 0);
            userManager.removeUser(email);
            loadUsersIntoTable(userModel); // Tabloyu güncelle
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }

    private void loadUsersIntoTable(DefaultTableModel userModel) {
        userModel.setRowCount(0); // Tabloyu temizle
        for (User user : userManager.getAllUsers().values()) {
            userModel.addRow(new Object[]{user.getEmail(), user.getName(), user.getRole()});
        }
    }
}
