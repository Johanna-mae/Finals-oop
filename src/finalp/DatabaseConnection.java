package finalp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        String configPath = "config" + File.separator + "db.properties";

        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
            
        } catch (IOException i) {
            System.out.println("Error reading config file: " + i.getMessage());
            throw new SQLException("Configuration file missing or unreadable");
        }
    }
}