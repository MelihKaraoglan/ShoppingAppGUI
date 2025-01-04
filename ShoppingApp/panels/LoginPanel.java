package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.ShoppingApp;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Kullanıcı Girişi", JLabel.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Kullanıcı Adı:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Şifre:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Giriş Yap");
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Ana uygulama sınıfında giriş işlemi
                ((ShoppingApp) SwingUtilities.getWindowAncestor(LoginPanel.this)).handleLogin(username, password);
            }
        });
    }
}
