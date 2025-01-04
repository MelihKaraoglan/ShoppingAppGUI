package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import Main.ShoppingApp;

public class UserPanel extends JPanel {
    public UserPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("User Paneli", JLabel.CENTER), BorderLayout.CENTER);
        // User'a özgü bileşenler buraya eklenebilir




        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new ShoppingApp();
        });
        add(logoutButton, BorderLayout.EAST);

    }
}

/*public AdminPanel(ProductManager productManager, UserManager userManager) {
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

    } */