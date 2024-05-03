package forex;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static forex.UserAuthenticator.authenticateUser;


public class LoginFrame extends JFrame {


    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;


    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setResizable(true);

        // Initialize components
        JPanel panel = new JPanel(new GridLayout(4, 1));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Add components to panel
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        // Add panel to frame
        add(panel);


        // Action listener for login button
            loginButton.addActionListener(e -> {
                String username = usernameField.getText();

            String password = new String(passwordField.getPassword());

            // Authenticate user using UserAuthenticator class
            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");



                new MainPageFrame().setVisible(true);
                MainPageFrame.nameOfLogin(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for register button
        registerButton.addActionListener(e -> {
            // Open registration window
            RegistrationFrame.showRegistrationFrame();
        });
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
