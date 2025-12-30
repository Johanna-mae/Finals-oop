package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;



class LoginScreen extends JFrame{
    private Frame mainFrame;

    private JTextField usernameField;
    private JPasswordField passwordField;
    
    
    public void setMainFrame(Frame frame) {
        this.mainFrame = frame;
    }
    
    public LoginScreen(){
        add(createLoginPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon image = new ImageIcon(Finalp.class.getResource("/finalp/images/logo-icon.png"));
        setIconImage(image.getImage());
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
         
        ImageIcon originalImage = new ImageIcon(getClass().getResource("images/bg-loan-image.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(960, 720, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundImageJLabel = new JLabel(scaledIcon);
        backgroundImageJLabel.setBounds(0, 0, 960, 720);
        backgroundImageJLabel.setLayout(null);

        JPanel boundingBox = new JPanel();
        boundingBox.setBounds(130, 110, 700, 500);
        boundingBox.setBackground(new Color(255, 255, 255, 215));
        boundingBox.setLayout(null);

        JLabel title = new JLabel("PaLoan System");
        title.setBounds(170, 65, 500, 56);
        title.setFont(new Font("Sans Serif", Font.BOLD, 50));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(190, 200, 165, 40);
        usernameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));       
        
        usernameField = new JTextField();
        usernameField.setBounds(360, 205, 150, 30);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(190, 250, 165, 40);
        passwordLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        
        passwordField = new JPasswordField();
        passwordField.setBounds(360, 255, 150, 30);
        
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(220, 380, 275, 64);
        loginButton.setFont(new Font("Sans Serif", Font.BOLD, 24));
        loginButton.setBackground(new Color(0xAAC3DD));
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(checkCredentials()){
                    mainFrame.setVisible(true);
                    dispose();
                } else {
                    System.out.println("Invalid credentials.");
                }
            }
        });
        
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                    loginButton.setBackground(new Color(0x8AA1B9));
            }
            public void mouseExited(MouseEvent e) {
                    loginButton.setBackground(new Color(0xAAC3DD));
            }
        });
        
        //panel.add(backgroundImageJLabel);
        boundingBox.add(title);
        boundingBox.add(usernameLabel);
        boundingBox.add(usernameField);
        boundingBox.add(passwordLabel);
        boundingBox.add(passwordField);
        boundingBox.add(new JLabel());
        boundingBox.add(loginButton);

        backgroundImageJLabel.add(boundingBox);

        panel.add(backgroundImageJLabel);

        return panel;
    }

    public boolean checkCredentials(){    
        String usernameToCheck = usernameField.getText().trim();
        String passwordToCheck = new String(passwordField.getPassword()).trim();
        
        String query = "SELECT username, password, role FROM Employee WHERE username = ? AND password = ? AND role = 'Admin'";
        
        System.out.println(usernameToCheck);
        System.out.println(passwordToCheck);

        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println(conn);

        // Check which database we're connected to
        PreparedStatement dbCheck = conn.prepareStatement("SELECT DATABASE()");
        ResultSet dbRs = dbCheck.executeQuery();
        dbRs.next();
        System.out.println("Connected to database: " + dbRs.getString(1));
        dbRs.close();
        dbCheck.close();
        
        // List all tables in current database
        PreparedStatement tablePs = conn.prepareStatement("SHOW TABLES");
        ResultSet tableRs = tablePs.executeQuery();
        System.out.println("Tables in database:");
        while(tableRs.next()) {
            System.out.println("  - " + tableRs.getString(1));
        }
        tableRs.close();
        tablePs.close();

            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(query);
            System.out.println(ps.toString());
            ps.setString(1, usernameToCheck);
            System.out.println(ps);
            ps.setString(2, passwordToCheck);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            boolean isAuthorized = rs.next();
            rs.close();
            ps.close();
            System.out.println(isAuthorized);
            return isAuthorized;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }       
    }
}