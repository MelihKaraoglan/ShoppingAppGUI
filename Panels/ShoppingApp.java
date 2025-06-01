package Panels;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.Products;
import Entities.UserInformations;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.*;

public class ShoppingApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    ArrayList<UserInformations> users = new ArrayList<>();
    ArrayList<Products> products = new ArrayList<>(); // bunu kullanmıyorum onun yerine products database'den çekiyorum ama dursun şimdilik
    
    
    private JList<String> productList;
    private DefaultListModel<String> listModel;
    
    

    private static final String urlsql = "jdbc:mysql://localhost:3306/shoppingapp"; 
    private static final String usernamesql = "root"; 
    private static final String passwordsql = ""; // Your MySQL password here 

    
    private static UserInformations loggedInUser;
    
    
    public ShoppingApp(UserInformations user) {
        this.loggedInUser = user; // Kullanıcı bilgisini sakla
        
        setResizable(false);
        setBackground(new Color(190, 174, 152));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 644, 466);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 235, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome, " + loggedInUser.getName() + "!");
        lblWelcome.setBounds(10, 10, 200, 30);
        contentPane.add(lblWelcome);
        
        
        JLabel lblProductDetails = new JLabel("DETAILS");
        lblProductDetails.setBounds(220, 50, 194, 43);
        contentPane.add(lblProductDetails);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // kullanıcı düzenleyemesin
        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBounds(220, 77, 200, 241);
        contentPane.add(textScrollPane);

        
        
        JSeparator separator = new JSeparator();
        separator.setBounds(-61, 37, 717, 3);
        contentPane.add(separator);
        
        JLabel lblProducts = new JLabel("PRODUCTS");
        lblProducts.setBounds(10, 50, 194, 43);
        contentPane.add(lblProducts);
        
        listModel = new DefaultListModel<>();
        productList = new JList<>(listModel);
        productList.setBounds(10, 77, 200, 241);
        
        // Kaydırma çubuğu ekleyelim
        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setBounds(10, 77, 200, 241);
        contentPane.add(scrollPane);
        
        
        
        productList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String selected = productList.getSelectedValue(); // seçili öğeyi al
                
                if (selected != null) {
                    String[] parts = selected.split(" ");
                    int selectedProductId = Integer.parseInt(productList.getSelectedValue().toString().split(" ")[0]);

                    try (Connection conn = DriverManager.getConnection(urlsql, usernamesql, passwordsql);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE id = " + selectedProductId)) {

                        if (rs.next()) {
                            String name = rs.getString("name");
                            double price = rs.getDouble("price");

                            String details = "Name: " + name + "\n"
                                           + "Price: " + price + "\n"
                                           + "Stock: " + rs.getInt("stock") + "\n";
                            textArea.setText(details);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        
        
        
        
      

        
        loadProductsFromDatabase();

        loadUsersFromDatabase();
    }
    
    
    
    
  
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShoppingApp frame = new ShoppingApp(loggedInUser);
                    frame.setVisible(true);

                    // ✅ MySQL bağlantısını aç
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(urlsql, usernamesql, passwordsql);
                    Statement statement = connection.createStatement();

                    // ✅ SQL Sorgusunu çalıştır
                    String sql = "SELECT * FROM users"; 
                    ResultSet resultSet = statement.executeQuery(sql);

                    // ✅ Sonuçları konsola yazdır
                    while (resultSet.next()) {
                        
                        String name = resultSet.getString("name");
                        String surname = resultSet.getString("surname");
                        System.out.println(" Name: " + name + ", Surname: " + surname);
                    }

                    // ✅ Kaynakları kapat
                    resultSet.close();
                    statement.close();
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    
    
    /**
     * Create the frame.
     */
    /*public ShoppingApp() {
    	setResizable(false);
        setBackground(new Color(190, 174, 152));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 644, 466);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 235, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
    
    */
    
    
    private void loadProductsFromDatabase() {
        
        String query = "SELECT id,name FROM products"; // Tablo adını ve sütun adını doğru yaz

        try (Connection conn = DriverManager.getConnection(urlsql, usernamesql, passwordsql);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            listModel.clear(); // Önce listeyi temizleyelim

            while (rs.next()) {
            	String productID = rs.getString("id");
                String productName = rs.getString("name");
                listModel.addElement(productID + " " + productName); // Ürünleri liste modeline ekleyelim
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı bağlantısı hatası!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    private void loadUsersFromDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(urlsql, usernamesql, passwordsql);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users"; 
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                System.out.println(" Name: " + name + ", Surname: " + surname);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
     
    
        } 
    }
}
