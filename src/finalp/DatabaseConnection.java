package finalp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configuration - Change 'PaLoanSystem' to your actual database name
    private static final String URL = "jdbc:mysql://localhost:3306/PaLoanSystem";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // XAMPP default is empty

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // New driver class name for Connector/J 8.0+ and 9.0+
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successfully connected to XAMPP MySQL!");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found! Did you add the JAR to Libraries?");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed! Check if XAMPP MySQL is running.");
            e.printStackTrace();
        }
        return connection;
    }
}