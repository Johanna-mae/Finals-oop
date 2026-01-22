package finalp;

import java.awt.*;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

class LoginScreen extends JFrame {
    private Frame mainFrame;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public String employeeRole = "";

    public void setMainFrame(Frame frame) {
        this.mainFrame = frame;
    }

    public LoginScreen() {
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
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkCredentials()) {
                    mainFrame.setVisible(true);
                    dispose();
                } else {
                    System.out.println("Invalid credentials.");
                    JOptionPane.showMessageDialog(
                            LoginScreen.this,
                            "Invalid Credentials",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
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

        // panel.add(backgroundImageJLabel);
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

    public boolean checkCredentials() {
        String usernameToCheck = usernameField.getText().trim();
        String passwordToCheck = new String(passwordField.getPassword()).trim();

        String query = "SELECT username, password_hash, role FROM Employee WHERE username = ? AND is_Active = 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, usernameToCheck);

            try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password_hash");
                        employeeRole = rs.getString("role");
                        boolean passwordMatch = false;

                        // Check if password is BCrypt hashed (starts with $2a$, $2b$, or $2y$)
                            if (storedPassword != null && storedPassword.startsWith("$2")) {
                            // New BCrypt hashed password - verify with BCrypt
                            passwordMatch = BCrypt.checkpw(passwordToCheck, storedPassword);

                            // If password matches, upgrade it to BCrypt hash
                                if (passwordMatch) {
                                String hashedPassword = BCrypt.hashpw(passwordToCheck, BCrypt.gensalt(10));
                                updatePasswordInDatabase(usernameToCheck, hashedPassword);
                                System.out.println("Password upgraded to BCrypt hash for user: " + usernameToCheck);
                                }
                            }
                        System.out.println("Password match: " + passwordMatch);
                        return passwordMatch;
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add this method to update passwords in the database
    private void updatePasswordInDatabase(String username, String hashedPassword) {
        String updateQuery = "UPDATE Employee SET password_hash = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            
            ps.setString(1, hashedPassword);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update password for user: " + username);
            e.printStackTrace();
        }
    }
}