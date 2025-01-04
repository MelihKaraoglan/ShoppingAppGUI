package Main;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// Kod şuan sıkıntıda değil çalışması gerekiyor ama datastorage kısmında bir hata var onu düzeltirseniz çalışacaktır. Hata library kısmında görmüyor, libraryi'i ekleyin.


import panels.AdminPanel;
import panels.UserPanel;
import panels.LoginPanel;
import models.admin.ProductManager;
import models.admin.UserAndAdminList;
import models.admin.UserManager;
import models.shared.Product;
import models.shared.User;
import models.user.Cart;
import utils.DataStorage;

public class ShoppingApp extends JFrame implements ActionListener {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private static Scanner scanner = new Scanner(System.in);
    private static ProductManager productManager = new ProductManager();
    private static UserManager userManager = new UserManager();
    private static UserAndAdminList userAndAdminList = new UserAndAdminList();

    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public static void main(String[] args) {
        System.out.println("Application started");
        SwingUtilities.invokeLater(() -> {
            System.out.println("Launching ShoppingApp GUI");
            new ShoppingApp();
        });
    }

    public ShoppingApp() {
        System.out.println("Initializing ShoppingApp GUI components");

        setTitle("Shopping App");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 80, 30);
        emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 230, 300, 30);
        statusLabel.setForeground(Color.BLACK);
        contentPane.add(statusLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        loginButton.addActionListener(this);

        contentPane.add(emailLabel);
        contentPane.add(emailField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(loginButton);

        setVisible(true);
        System.out.println("ShoppingApp GUI is now visible");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Login button clicked");

        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println("Attempting to validate user with email: " + email);
        User validatedUser = userAndAdminList.validateUser(email, password);

        if (validatedUser != null) {
            System.out.println("User validated: " + validatedUser.getName() + " (Role: " + validatedUser.getRole() + ")");
            if (validatedUser.getRole() == User.Role.ADMIN) {
                statusLabel.setText("Admin olarak giriş yaptınız!");
                statusLabel.setForeground(Color.BLUE);
                openMenu(validatedUser);
            } else if (validatedUser.getRole() == User.Role.USER) {
                statusLabel.setText("Kullanıcı olarak giriş yaptınız!");
                statusLabel.setForeground(Color.GREEN);
                openMenu(validatedUser);
            }
        } else {
            System.out.println("User validation failed for email: " + email);
            statusLabel.setText("Geçersiz email veya şifre.");
            statusLabel.setForeground(Color.RED);
        }
    }

    public void openMenu(User user) {
        System.out.println("Opening menu for user: " + user.getName() + " (Role: " + user.getRole() + ")");
        getContentPane().removeAll(); // Önceki içerikleri temizle
    
        if (user.getRole() == User.Role.ADMIN) {
            System.out.println("Loading AdminPanel");
            AdminPanel adminPanel = new AdminPanel(productManager, userManager);
            adminPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
            adminPanel.setVisible(true);
            getContentPane().add(adminPanel);
        } else if (user.getRole() == User.Role.USER) {
            System.out.println("Loading UserPanel");
            UserPanel userPanel = new UserPanel();
            userPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
            userPanel.setVisible(true);
            getContentPane().add(userPanel);
        }
    
        revalidate();
        repaint();
        System.out.println("Menu loaded successfully");
    }

    @Override
    public void dispose() {
        System.out.println("Saving data before exiting...");
        productManager.saveProducts();
        userManager.saveUsers(); // Assuming userAndAdminList has integration with UserManager
        super.dispose();
    }
}
