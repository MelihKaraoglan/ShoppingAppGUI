package Panels;

import java.awt.EventQueue;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.UserInformations;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtNameLogon;
	private JTextField txtSurnameLogon;
	private JTextField txtAgeLogon;
	private JTextField txtPasswordLogon;
	private JTextField txtUsernameLogon;
	
	
	
	private static final String urlsql = "jdbc:mysql://localhost:3306/shoppingapp";
	private static final String usernamesql = "root";
	private static final String passwordsql = ""; 
	

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setResizable(false);
		setBackground(new Color(190, 174, 152));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(96, 95, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 138, 96, 19);
		contentPane.add(passwordField);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(10, 98, 70, 13);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 141, 70, 13);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
		        String password = new String(passwordField.getPassword());

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection connection = DriverManager.getConnection(urlsql, usernamesql, passwordsql);
		            Statement statement = connection.createStatement();
		            String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
		            ResultSet resultSet = statement.executeQuery(sql);

		            
		            //bunu galiba ShoppingApp'e geçirmemiz lazım
		            if (resultSet.next()) {
		                // Kullanıcı bilgilerini al
		                String name = resultSet.getString("name");
		                String surname = resultSet.getString("surname");
		                int age = resultSet.getInt("age");
		                String role = resultSet.getString("role");

		                // UserInformations nesnesi oluştur
		                UserInformations user = new UserInformations(name, surname, username, password, role, age);

		                // ShoppingApp aç
		                ShoppingApp app = new ShoppingApp(user);
		                app.setVisible(true);
		                dispose(); // LoginPage'i kapat

		            } else {
		                JOptionPane.showMessageDialog(contentPane, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
		                
		            
		            }

		            resultSet.close();
		            statement.close();
		            connection.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
			}
		});
		btnLogin.setBounds(96, 184, 96, 21);
		contentPane.add(btnLogin);
		
		JButton btnLogon = new JButton("Log on");
		btnLogon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setBounds(100, 100, 561, 411);
		
			}
		});
		btnLogon.setBounds(96, 215, 96, 21);
		contentPane.add(btnLogon);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(288, 70, 206, 235);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNameLogon = new JLabel("Name");
		lblNameLogon.setBounds(10, 32, 58, 13);
		panel.add(lblNameLogon);
		
		JLabel lblSurnameLogon = new JLabel("Surname");
		lblSurnameLogon.setBounds(10, 55, 58, 13);
		panel.add(lblSurnameLogon);
		
		JLabel lblAgeLogon = new JLabel("Age");
		lblAgeLogon.setBounds(10, 78, 58, 13);
		panel.add(lblAgeLogon);
		
		JLabel lblusernameLogon = new JLabel("Username");
		lblusernameLogon.setBounds(10, 101, 58, 13);
		panel.add(lblusernameLogon);
		
		JLabel lblPasswordLogon = new JLabel("Password");
		lblPasswordLogon.setBounds(10, 124, 58, 13);
		panel.add(lblPasswordLogon);
		
		
		
		txtNameLogon = new JTextField();
		txtNameLogon.setBounds(82, 29, 96, 19);
		panel.add(txtNameLogon);
		txtNameLogon.setColumns(10);
		
		txtSurnameLogon = new JTextField();
		txtSurnameLogon.setColumns(10);
		txtSurnameLogon.setBounds(82, 52, 96, 19);
		panel.add(txtSurnameLogon);
		
		txtAgeLogon = new JTextField();
		txtAgeLogon.setColumns(10);
		txtAgeLogon.setBounds(82, 75, 96, 19);
		panel.add(txtAgeLogon);
		
		txtPasswordLogon = new JTextField();
		txtPasswordLogon.setColumns(10);
		txtPasswordLogon.setBounds(82, 121, 96, 19);
		panel.add(txtPasswordLogon);
		
		txtUsernameLogon = new JTextField();
		txtUsernameLogon.setColumns(10);
		txtUsernameLogon.setBounds(82, 98, 96, 19);
		panel.add(txtUsernameLogon);
		
		JLabel lblStatusForSaving = new JLabel("");
		lblStatusForSaving.setBounds(10, 212, 150, 13);
		panel.add(lblStatusForSaving);
		
		
		
		JButton btnSaveLogon = new JButton("SAVE");
		btnSaveLogon.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String name = txtNameLogon.getText().trim();
		        String surname = txtSurnameLogon.getText().trim();
		        String username = txtUsernameLogon.getText().trim();
		        String password = txtPasswordLogon.getText().trim();
		        String role = "CUSTOMER";

		        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty() || txtAgeLogon.getText().trim().isEmpty()) {
		            lblStatusForSaving.setText("Please fill all fields.");
		            lblStatusForSaving.setForeground(Color.RED);
		            return;
		        }

		        int age;
		        try {
		            age = Integer.parseInt(txtAgeLogon.getText().trim());
		        } catch (NumberFormatException ex) {
		            lblStatusForSaving.setText("Age must be a number!");
		            lblStatusForSaving.setForeground(Color.RED);
		            return;
		        }

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection connection = DriverManager.getConnection(urlsql, usernamesql, passwordsql);

		            // Kullanıcı adı var mı kontrolü
		            String checkUsernameSql = "SELECT COUNT(*) FROM users WHERE username = ?";
		            PreparedStatement checkStmt = connection.prepareStatement(checkUsernameSql);
		            checkStmt.setString(1, username);
		            ResultSet rs = checkStmt.executeQuery();
		            rs.next();
		            int count = rs.getInt(1);
		            rs.close();
		            checkStmt.close();

		            if (count > 0) {
		                lblStatusForSaving.setText("Username already exists!");
		                lblStatusForSaving.setForeground(Color.RED);
		                connection.close();
		                return;
		            }

		            // Ekleme işlemi
		            String sql = "INSERT INTO users (name, surname, username, password, role, age) VALUES (?, ?, ?, ?, ?, ?)";
		            PreparedStatement stmt = connection.prepareStatement(sql);
		            stmt.setString(1, name);
		            stmt.setString(2, surname);
		            stmt.setString(3, username);
		            stmt.setString(4, password);
		            stmt.setString(5, role);
		            stmt.setInt(6, age);

		            int rowsInserted = stmt.executeUpdate();
		            if (rowsInserted > 0) {
		                lblStatusForSaving.setText("User saved successfully.");
		                lblStatusForSaving.setForeground(Color.GREEN);
		                txtNameLogon.setText("");
		                txtSurnameLogon.setText("");
		                txtUsernameLogon.setText("");
		                txtPasswordLogon.setText("");
		                txtAgeLogon.setText("");
		            } else {
		                lblStatusForSaving.setText("Error while saving user.");
		                lblStatusForSaving.setForeground(Color.RED);
		            }

		            stmt.close();
		            connection.close();
		        } catch (Exception ex) {
		            lblStatusForSaving.setText("Something went wrong!");
		            ex.printStackTrace();
		        }
		    }
		});

		btnSaveLogon.setBounds(82, 157, 96, 21);
		panel.add(btnSaveLogon);
		
		
		
		
		
		
	}
}
