package finalp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Testing MariaDB connection...");
        System.out.println("Java version: " + System.getProperty("java.version"));
        
        try {
            // Load config
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("config/db.properties");
            props.load(fis);
            fis.close();
            
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            
            System.out.println("Connecting to: " + url);
            System.out.println("User: " + user);
            
            // Test connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✓ Connection successful!");
            System.out.println("✓ Database: " + conn.getCatalog());
            System.out.println("✓ Driver: " + conn.getMetaData().getDriverName());
            System.out.println("✓ Driver version: " + conn.getMetaData().getDriverVersion());
            
            conn.close();
            System.out.println("✓ Connection closed cleanly");
            
        } catch (java.io.FileNotFoundException e) {
            System.err.println("✗ config/db.properties not found!");
            System.err.println("Did you copy db.properties.example to db.properties?");
        } catch (SQLException e) {
            System.err.println("✗ Database connection failed!");
            System.err.println("Check your database credentials and that MariaDB is running");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error!");
            e.printStackTrace();
        }
    }
}