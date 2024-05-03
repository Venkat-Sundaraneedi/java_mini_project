package forex;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {

    private static final String USER_FILE_PATH = "E:\\programming2\\lpu\\java mini project\\users.txt";

    public static void saveUsersFromDatabase() {
        List<String[]> users = getUsersFromDatabase();

        Path path = Paths.get(USER_FILE_PATH);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            // Write each user's information to the file
            users.stream()
                    .map(user -> STR."\{user[0]},\{user[1]}") // Assuming user array structure is [username, password]
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> getUsersFromDatabase() {
        List<String[]> users = new ArrayList<>();

        // JDBC connection parameters
        String url = "jdbc:mysql://127.0.0.1:3306/forex";
        String dbUsername = "root";
        String dbPassword = "notme";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Prepare SQL statement
            String sql = "SELECT username, password FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Execute query
                try (ResultSet rs = stmt.executeQuery()) {
                    // Iterate through the result set and add users to the list
                    while (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        users.add(new String[]{username, password});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Example usage
    public static void main(String[] args) {
        // Save users from the database to the file
        saveUsersFromDatabase();

        System.out.println("Users saved to file successfully.");
    }
}
