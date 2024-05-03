package forex;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class RegistrationFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationFrame() {
        setTitle("Register");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose frame on close
        setLocationRelativeTo(null); // Center the frame
        setResizable(true);

        // Initialize components
        JPanel panel = new JPanel(new GridLayout(3, 1));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");

        // Add components to panel
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(registerButton);

        // Add panel to frame
        add(panel);

        // Action listener for register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Register user
                if (registerUser(username, password)) {
                    JOptionPane.showMessageDialog(RegistrationFrame.this, "Registration successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegistrationFrame.this, "Error registering user", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean registerUser(String username, String password) {
        // JDBC connection parameters
        String url = "jdbc:mysql://127.0.0.1:3306/forex";
        String dbUsername = "root";
        String dbPassword = "notme";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Prepare SQL statement
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                // Execute update
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0; // Return true if user was inserted successfully
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void showRegistrationFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegistrationFrame registrationFrame = new RegistrationFrame();
                registrationFrame.setVisible(true);
            }
        });
    }
}
