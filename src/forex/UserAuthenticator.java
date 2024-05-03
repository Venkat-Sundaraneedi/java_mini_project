package forex;

import java.sql.*;

public class UserAuthenticator {
    public static boolean authenticateUser(String username, String password) {
        // JDBC connection parameters
        String url = "jdbc:mysql://127.0.0.1:3306/forex";
        String dbUsername = "root";
        String dbPassword = "notme";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Prepare SQL statement
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                // Execute query
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); // Return true if user exists
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
